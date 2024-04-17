package io.fabric8.testing.istio;

import io.fabric8.istio.api.networking.v1alpha3.VirtualService;
import io.fabric8.istio.client.IstioClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;

public class VirtualServiceCreateDemo {
  public static void main(String[] args) {
    try (KubernetesClient kubernetesClient = new KubernetesClientBuilder().build()) {
      IstioClient istioClient = kubernetesClient.adapt(IstioClient.class);
      VirtualService virtualService = istioClient.v1alpha3().virtualServices().load(VirtualServiceCreateDemo.class.getResourceAsStream("/virtualservice.yml")).item();

      istioClient.v1alpha3().virtualServices().resource(virtualService).create();
      System.out.println("Successfully created VirtualService!");
    }
  }
}
