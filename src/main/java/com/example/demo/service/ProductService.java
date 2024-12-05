package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProductWithImage(Product product, MultipartFile imageFile) throws IOException {
        if (!imageFile.isEmpty()) {
            String fileName = imageFile.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            product.setFileName(fileName);
            product.setFilePath(filePath.toString());
            product.setFileType(imageFile.getContentType());
            product.setUploadTime(LocalDateTime.now());
        }
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public void updateProductWithImage(Long id, Product updatedProduct, MultipartFile imageFile) throws IOException {
        Optional<Product> existingProductOpt = getProductById(id);
        if (existingProductOpt.isPresent()) {
            Product product = existingProductOpt.get();
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            product.setQuantity(updatedProduct.getQuantity());

            if (!imageFile.isEmpty()) {
                // Delete old file if it exists
                Path oldFilePath = Paths.get(product.getFilePath());
                Files.deleteIfExists(oldFilePath);

                // Save new file to filesystem
                String fileName = imageFile.getOriginalFilename();
                Path newFilePath = Paths.get(uploadDir, fileName);
                Files.copy(imageFile.getInputStream(), newFilePath, StandardCopyOption.REPLACE_EXISTING);

                product.setFileName(fileName);
                product.setFilePath(newFilePath.toString());
                product.setFileType(imageFile.getContentType());
                product.setUploadTime(LocalDateTime.now());
            }
            productRepository.save(product);
        }
    }

    public void deleteProduct(Long id) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            Path filePath = Paths.get(product.getFilePath());
            try {
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            productRepository.deleteById(id);
        }
    }
}
