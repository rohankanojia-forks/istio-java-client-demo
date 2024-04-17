package io.fabric8.testing.istio;

import io.fabric8.istio.api.networking.v1alpha3.EnvoyFilter;
import io.fabric8.istio.client.IstioClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;

public class EnvoyFilterCreateDemo {
  public static void main(String[] args) {
    try (KubernetesClient kubernetesClient = new KubernetesClientBuilder().build()) {
      IstioClient istioClient = kubernetesClient.adapt(IstioClient.class);
      EnvoyFilter envoyFilter = istioClient.v1alpha3().envoyFilters().load(EnvoyFilterCreateDemo.class.getResourceAsStream("/envoyfilter.yml")).item();

      istioClient.v1alpha3().envoyFilters().resource(envoyFilter).create();
      System.out.println("Successfully created EnvoyFilter!");
    }
  }
}
