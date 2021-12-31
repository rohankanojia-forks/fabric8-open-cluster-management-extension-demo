package io.fabric8.openshift.api.demo;

import io.fabric8.kubernetes.api.model.Secret;
import io.fabric8.kubernetes.api.model.SecretBuilder;
import io.fabric8.kubernetes.client.utils.Serialization;
import io.fabric8.openshift.api.model.installer.v1.InstallConfig;
import io.fabric8.openshift.client.DefaultOpenShiftClient;
import io.fabric8.openshift.client.OpenShiftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class InstallConfigSecretDemo {
  private static final Logger logger = LoggerFactory.getLogger(InstallConfigSecretDemo.class.getSimpleName());

  public static void main(String[] args) {
    try (OpenShiftClient openShiftClient = new DefaultOpenShiftClient()) {
      File installConfigYamlFile = new File(InstallConfigSecretDemo.class.getResource("/install-config.yaml").getFile());
      InstallConfig installConfig = Serialization.yamlMapper().readValue(installConfigYamlFile, InstallConfig.class);

      logger.info("Creating Install Config Secret");
      Secret secret = openShiftClient.secrets().inNamespace("default").create(new SecretBuilder()
              .withNewMetadata().withName("test-installconfig").endMetadata()
              .addToData("install-config.yaml", new String(Base64.getEncoder().encode(Serialization.yamlMapper().writeValueAsString(installConfig).getBytes())))
          .build());
      logger.info("SUCCESS. Secret {} created successfully.", secret.getMetadata().getNamespace());
    } catch (IOException e) {
      logger.error("Error in creating Install Config ", e);
    }
  }
}
