/**
 * Copyright (C) 2015 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.fabric8.openclustermanagement.demo;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.openclustermanagement.api.model.cluster.v1.ManagedCluster;
import io.fabric8.openclustermanagement.client.DefaultOpenClusterManagementClient;
import io.fabric8.openclustermanagement.client.OpenClusterManagementClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManagedClusterList {
  private static final Logger logger = LoggerFactory.getLogger(ManagedClusterList.class.getSimpleName());

  public static void main(String[] args) {
    try (OpenClusterManagementClient ocmClient = new DefaultOpenClusterManagementClient()) {
      logger.info("Listing all ManagedClusters: ");
      ocmClient.clusters().managedClusters()
        .list().getItems().stream()
        .map(ManagedCluster::getMetadata)
        .map(ObjectMeta::getName)
        .forEach(logger::info);
    }
  }
}
