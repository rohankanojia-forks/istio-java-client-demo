package io.fabric8.testing.istio;

import io.fabric8.istio.api.networking.v1beta1.ProxyConfig;
import io.fabric8.istio.client.IstioClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;

public class ProxyConfigCreateDemo {
  public static void main(String[] args) {
    try (KubernetesClient kubernetesClient = new KubernetesClientBuilder().build()) {
      IstioClient istioClient = kubernetesClient.adapt(IstioClient.class);
      ProxyConfig destinationRule = istioClient.v1beta1().proxyConfigs().load(ProxyConfigCreateDemo.class.getResourceAsStream("/proxyconfig.yml")).item();

      istioClient.v1beta1().proxyConfigs().resource(destinationRule).create();
      System.out.println("Successfully created ProxyConfig!");
    }
  }
}
