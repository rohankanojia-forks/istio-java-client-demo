package io.fabric8.testing.istio;

import io.fabric8.istio.api.networking.v1alpha3.WorkloadGroup;
import io.fabric8.istio.client.IstioClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;

public class WorkloadGroupCreateDemo {
  public static void main(String[] args) {
    try (KubernetesClient kubernetesClient = new KubernetesClientBuilder().build()) {
      IstioClient istioClient = kubernetesClient.adapt(IstioClient.class);
      WorkloadGroup destinationRule = istioClient.v1alpha3().workloadGroups().load(WorkloadGroupCreateDemo.class.getResourceAsStream("/workloadgroups.yml")).item();

      istioClient.v1alpha3().workloadGroups().resource(destinationRule).create();
      System.out.println("Successfully created WorkloadGroup!");
    }
  }
}
