package io.fabric8.testing.istio;

import io.fabric8.istio.api.networking.v1alpha3.Sidecar;
import io.fabric8.istio.client.IstioClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;

public class SidecarCreateDemo {
  public static void main(String[] args) {
    try (KubernetesClient kubernetesClient = new KubernetesClientBuilder().build()) {
      IstioClient istioClient = kubernetesClient.adapt(IstioClient.class);
      Sidecar sidecar = istioClient.v1alpha3().sidecars().load(SidecarCreateDemo.class.getResourceAsStream("/sidecar.yml")).item();

      istioClient.v1alpha3().sidecars().resource(sidecar).create();
      System.out.println("Successfully created Sidecar!");
    }
  }
}
