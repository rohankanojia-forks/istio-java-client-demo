package io.fabric8.testing.istio;

import io.fabric8.istio.api.networking.v1alpha3.Gateway;
import io.fabric8.istio.client.IstioClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;

public class GatewayDemo {
  public static void main(String[] args) {
    try (KubernetesClient kubernetesClient = new KubernetesClientBuilder().build()) {
      IstioClient istioClient = kubernetesClient.adapt(IstioClient.class);
      Gateway gateway = istioClient.v1alpha3().gateways().load(GatewayDemo.class.getResourceAsStream("/gateway.yml")).item();

      istioClient.v1alpha3().gateways().resource(gateway).create();
      System.out.println("Successfully created Gateway!");
    }
  }
}
