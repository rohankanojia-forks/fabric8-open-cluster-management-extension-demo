# Fabric8 K8s Client Open Cluster Management Extension Demo

This repository gives a quick overview of [Open Cluster Management](https://open-cluster-management.io/) extension.

It contains some basic samples regarding some Open Cluster Management resources like:

- ManagedCluster
- ManagedClusterSet
- KlusterletAddonConfig
- PlacementRuleCreate

There are some examples of OpenShift Hive(`hive.openshift.io`) resources as well since they're related to cluster management too
- ClusterDeployment
- MachinePool
- InstallConfig (equivalent of `oc create secret generic mycluster-install-config --from-file=install-config.yaml=./install-config.yaml
`)

## Pre-requisite:
- A Kubernetes or Red Hat OpenShift Cluster
- [Red Hat Advanced Cluster Management for Kubernetes](https://access.redhat.com/documentation/en-us/red_hat_advanced_cluster_management_for_kubernetes/2.3) installed on cluster

## How to Build?
```sh
$ mvn clean install
```

## How to Run?
Once you've setup your environment, you can run any sample using maven exec plugin. Here is an example:
```sh
$ mvn exec:java -Dexec.mainClass=io.fabric8.openshift.api.demo.InstallConfigSecretDemo
```

