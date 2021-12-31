package io.fabric8.openshift.api.demo;

import io.fabric8.openshift.api.model.hive.v1.MachinePool;
import io.fabric8.openshift.api.model.hive.v1.MachinePoolBuilder;
import io.fabric8.openshift.client.DefaultOpenShiftClient;
import io.fabric8.openshift.client.OpenShiftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MachinePoolDemo {
  private static final Logger logger = LoggerFactory.getLogger(MachinePoolDemo.class.getSimpleName());

  public static void main(String[] args) {
    try (OpenShiftClient openShiftClient = new DefaultOpenShiftClient()) {
      MachinePool machinePool = new MachinePoolBuilder()
          .withNewMetadata()
          .withName("test-machinepool")
          .endMetadata()
          .withNewSpec()
          .withNewClusterDeploymentRef()
          .withName("foo")
          .endClusterDeploymentRef()
          .withName("worker")
          .withReplicas(3L)
          .withNewPlatform()
          .withNewAws()
          .withType("m4.large")
          .withNewRootVolume()
          .withIops(100)
          .withSize(22)
          .withType("gp2")
          .endRootVolume()
          .endAws()
          .endPlatform()
          .endSpec()
          .build();

      logger.info("Creating MachinePool...");
      openShiftClient.hive().machinePools().inNamespace("default").createOrReplace(machinePool);
      logger.info("SUCCESS");
    }
  }
}
