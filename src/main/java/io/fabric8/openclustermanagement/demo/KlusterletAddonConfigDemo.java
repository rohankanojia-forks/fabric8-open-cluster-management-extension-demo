package io.fabric8.openclustermanagement.demo;

import io.fabric8.openclustermanagement.api.model.agent.v1.KlusterletAddonConfig;
import io.fabric8.openclustermanagement.api.model.agent.v1.KlusterletAddonConfigBuilder;
import io.fabric8.openclustermanagement.client.DefaultOpenClusterManagementClient;
import io.fabric8.openclustermanagement.client.OpenClusterManagementClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KlusterletAddonConfigDemo {
  private static final Logger logger = LoggerFactory.getLogger(KlusterletAddonConfigDemo.class.getSimpleName());

  public static void main(String[] args) {
    try (OpenClusterManagementClient ocmClient = new DefaultOpenClusterManagementClient()) {
      KlusterletAddonConfig kac = new KlusterletAddonConfigBuilder()
          .withNewMetadata()
          .withName("test-kac")
          .endMetadata()
          .withNewSpec()
          .withNewApplicationManager()
          .withEnabled(true)
          .endApplicationManager()
          .withNewPolicyController()
          .withEnabled(true)
          .endPolicyController()
          .withNewSearchCollector()
          .withEnabled(true)
          .endSearchCollector()
          .withNewCertPolicyController()
          .withEnabled(true)
          .endCertPolicyController()
          .withNewIamPolicyController()
          .withEnabled(true)
          .endIamPolicyController()
          .endSpec()
          .build();

      logger.info("Creating KlusterletAddonConfig");
      ocmClient.agents().klusterletAddonConfigs().inNamespace("default").create(kac);
      logger.info("SUCCESS");
    }
  }
}
