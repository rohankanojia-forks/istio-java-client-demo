package io.fabric8.testing.istio;

import io.fabric8.istio.api.networking.v1alpha3.WorkloadEntry;
import io.fabric8.istio.client.IstioClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;

public class WorkloadEntryCreateDemo {
  public static void main(String[] args) {
    try (KubernetesClient kubernetesClient = new KubernetesClientBuilder().build()) {
      IstioClient istioClient = kubernetesClient.adapt(IstioClient.class);
      WorkloadEntry destinationRule = istioClient.v1alpha3().workloadEntries().load(WorkloadEntryCreateDemo.class.getResourceAsStream("/workloadentry.yml")).item();

      istioClient.v1alpha3().workloadEntries().resource(destinationRule).create();
      System.out.println("Successfully created WorkloadEntry!");
    }
  }
}
