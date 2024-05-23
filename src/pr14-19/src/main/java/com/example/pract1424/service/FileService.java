package ru.gavrilovds.prac14.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@ManagedResource
@RequiredArgsConstructor
public class FileService {
  private final MarketService marketService;
  private final ProductService productService;
  private final ObjectMapper mapper;

  @Value("${application.persistent-dir}")
  private String persistentDir;

  @SneakyThrows
  @ManagedOperation
  @Scheduled(fixedDelay = 30, timeUnit = TimeUnit.MINUTES)
  public void persist() {
    File dir = new File(persistentDir);
    if (dir.exists()) {
      FileUtils.deleteDirectory(dir);
    }
    dir.mkdirs();
    new File(dir, "products").mkdir();
    new File(dir, "markets").mkdir();
    saveAllEntities(dir);
  }

  private void saveAllEntities(File dir) {
    marketService.getMarkets().forEach(
        marketDto -> {
          try {
            Files.write(dir.toPath().resolve("markets").resolve(marketDto.getName() + ".json"),
                mapper.writeValueAsBytes(marketDto)
            );
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        });
    productService.getProducts().forEach(
        productDto -> {
          try {
            Files.write(dir.toPath().resolve("products").resolve(productDto.getName() + ".json"),
                mapper.writeValueAsBytes(productDto)
            );
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        }
    );
  }
}

