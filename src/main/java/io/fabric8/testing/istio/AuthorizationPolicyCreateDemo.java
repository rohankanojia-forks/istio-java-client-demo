package io.fabric8.testing.istio;

import io.fabric8.istio.api.security.v1beta1.AuthorizationPolicy;
import io.fabric8.istio.client.IstioClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;

public class AuthorizationPolicyCreateDemo {
  public static void main(String[] args) {
    try (KubernetesClient kubernetesClient = new KubernetesClientBuilder().build()) {
      IstioClient istioClient = kubernetesClient.adapt(IstioClient.class);
      AuthorizationPolicy authorizationPolicy = istioClient.v1beta1().authorizationPolicies().load(AuthorizationPolicyCreateDemo.class.getResourceAsStream("/authorizationpolicy.yml")).item();

      istioClient.v1beta1().authorizationPolicies().resource(authorizationPolicy).create();
      System.out.println("Successfully created AuthorizationPolicy!");
    }
  }
}
