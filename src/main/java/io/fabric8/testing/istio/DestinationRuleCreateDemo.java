package io.fabric8.testing.istio;

import io.fabric8.istio.api.networking.v1alpha3.DestinationRule;
import io.fabric8.istio.client.IstioClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;

public class DestinationRuleCreateDemo {
  public static void main(String[] args) {
    try (KubernetesClient kubernetesClient = new KubernetesClientBuilder().build()) {
      IstioClient istioClient = kubernetesClient.adapt(IstioClient.class);
      DestinationRule destinationRule = istioClient.v1alpha3().destinationRules().load(DestinationRuleCreateDemo.class.getResourceAsStream("/destination-rule.yml")).item();

      istioClient.v1alpha3().destinationRules().resource(destinationRule).create();
      System.out.println("Successfully created DestinationRule!");
    }
  }
}
