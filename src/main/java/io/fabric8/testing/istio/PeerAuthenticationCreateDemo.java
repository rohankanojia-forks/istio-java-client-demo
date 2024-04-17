package io.fabric8.testing.istio;

import io.fabric8.istio.api.security.v1beta1.PeerAuthentication;
import io.fabric8.istio.client.IstioClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;

public class PeerAuthenticationCreateDemo {
  public static void main(String[] args) {
    try (KubernetesClient kubernetesClient = new KubernetesClientBuilder().build()) {
      IstioClient istioClient = kubernetesClient.adapt(IstioClient.class);
      PeerAuthentication peerAuthentication = istioClient.v1beta1().peerAuthentications().load(PeerAuthenticationCreateDemo.class.getResourceAsStream("/peerauthentication.yml")).item();

      istioClient.v1beta1().peerAuthentications().resource(peerAuthentication).create();
      System.out.println("Successfully created PeerAuthentication!");
    }
  }
}
