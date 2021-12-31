package io.fabric8.openshift.api.demo;

import io.fabric8.openshift.api.model.hive.v1.ClusterDeployment;
import io.fabric8.openshift.api.model.hive.v1.ClusterDeploymentBuilder;
import io.fabric8.openshift.client.DefaultOpenShiftClient;
import io.fabric8.openshift.client.OpenShiftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClusterDeploymentDemo {
  private static final Logger logger = LoggerFactory.getLogger(ClusterDeploymentDemo.class.getSimpleName());

  public static void main(String[] args) {
    try (OpenShiftClient openShiftClient = new DefaultOpenShiftClient()) {
      ClusterDeployment clusterDeployment = new ClusterDeploymentBuilder()
        .withNewMetadata()
        .withName("test-cluster-deployment")
        .endMetadata()
        .withNewSpec()
        .withPreserveOnDelete(false)
        .withClusterName("foo")
        .withBaseDomain("bar.baz")
        .withNewPlatform()
        .withNewAws()
        .withRegion("us-east-1")
        .withNewCredentialsSecretRef().withName("foo-aws-creds").endCredentialsSecretRef()
        .endAws()
        .endPlatform()
        .withNewProvisioning()
        .withNewInstallConfigSecretRef()
        .withName("foo-install-config")
        .endInstallConfigSecretRef()
        .withNewImageSetRef()
        .withName("clusterimageset-sample")
        .endImageSetRef()
        .endProvisioning()
        .withNewPullSecretRef()
        .withName("foo-pull-secret")
        .endPullSecretRef()
        .endSpec()
        .build();

      logger.info("Creating ClusterDeployment...");
      openShiftClient.hive().clusterDeployments().inNamespace("default").createOrReplace(clusterDeployment);
      logger.info("SUCCESS");
    }
  }
}
