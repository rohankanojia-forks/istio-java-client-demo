package io.fabric8.testing.istio;

import io.fabric8.istio.api.security.v1beta1.RequestAuthentication;
import io.fabric8.istio.client.IstioClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;

public class RequestAuthenticationCreateDemo {
  public static void main(String[] args) {
    try (KubernetesClient kubernetesClient = new KubernetesClientBuilder().build()) {
      IstioClient istioClient = kubernetesClient.adapt(IstioClient.class);
      RequestAuthentication destinationRule = istioClient.v1beta1().requestAuthentications().load(RequestAuthenticationCreateDemo.class.getResourceAsStream("/requestauthentication.yml")).item();

      istioClient.v1beta1().requestAuthentications().resource(destinationRule).create();
      System.out.println("Successfully created RequestAuthentication!");
    }
  }
}
