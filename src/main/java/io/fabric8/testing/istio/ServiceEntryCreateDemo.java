package io.fabric8.testing.istio;

import io.fabric8.istio.api.networking.v1alpha3.ServiceEntry;
import io.fabric8.istio.client.IstioClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;

public class ServiceEntryCreateDemo {
  public static void main(String[] args) {
    try (KubernetesClient kubernetesClient = new KubernetesClientBuilder().build()) {
      IstioClient istioClient = kubernetesClient.adapt(IstioClient.class);
      ServiceEntry destinationRule = istioClient.v1alpha3().serviceEntries().load(ServiceEntryCreateDemo.class.getResourceAsStream("/serviceentry.yml")).item();

      istioClient.v1alpha3().serviceEntries().resource(destinationRule).create();
      System.out.println("Successfully created ServiceEntry!");
    }
  }
}
