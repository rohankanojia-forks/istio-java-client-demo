package io.fabric8.testing.istio;

import io.fabric8.istio.api.security.v1beta1.AuthorizationPolicy;
import io.fabric8.istio.api.security.v1beta1.AuthorizationPolicyAction;
import io.fabric8.istio.api.security.v1beta1.AuthorizationPolicyBuilder;
import io.fabric8.istio.api.security.v1beta1.OperationBuilder;
import io.fabric8.istio.api.security.v1beta1.Rule;
import io.fabric8.istio.api.security.v1beta1.RuleBuilder;
import io.fabric8.istio.api.security.v1beta1.RuleFromBuilder;
import io.fabric8.istio.api.security.v1beta1.RuleToBuilder;
import io.fabric8.istio.api.security.v1beta1.SourceBuilder;
import io.fabric8.istio.api.type.v1beta1.WorkloadSelectorBuilder;
import io.fabric8.istio.client.IstioClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;

import java.util.Collections;
import java.util.List;

public class IstioAuthorizationPolicyCreate {
  public static void main(String[] args) {
    try (KubernetesClient kubernetesClient = new KubernetesClientBuilder().build()) {
      IstioClient istioClient = kubernetesClient.adapt(IstioClient.class);

      String policyName = "foo";
      String action = "DENY";
      String method = "foo";
      List<String> fromNamespaces = Collections.emptyList();
      AuthorizationPolicyBuilder builder = new AuthorizationPolicyBuilder()
          .withNewMetadata()
          .withName(policyName)
          .endMetadata()
          .withNewSpec()
          .withSelector(new WorkloadSelectorBuilder().withMatchLabels(Collections.singletonMap("app", "fabric8")).build())
          .withAction((action.equalsIgnoreCase("DENY")) ? AuthorizationPolicyAction.DENY : AuthorizationPolicyAction.ALLOW)
          .endSpec();

      if(!fromNamespaces.isEmpty()){
        for (String fromNamespace: fromNamespaces){
          builder.editSpec()
              .editFirstRule()
                  .addToFrom(new RuleFromBuilder().withSource(new SourceBuilder().withNamespaces(fromNamespace).build()).build())
                  .addToTo(new RuleToBuilder().withOperation(new OperationBuilder().withMethods(method).build()).build())
              .endRule()
              .endSpec();
        }
      }

      System.out.println("Creating a AuthorizationPolicy entry");
      AuthorizationPolicyBuilder builder2 = new AuthorizationPolicyBuilder()
          .withNewMetadata()
          .withName(policyName)
          .endMetadata()
          .withNewSpec()
          .withSelector(new WorkloadSelectorBuilder().withMatchLabels(Collections.singletonMap("app", "appLabel")).build())
          .withAction(AuthorizationPolicyAction.ALLOW)
          .endSpec();

      AuthorizationPolicy authorizationPolicy = builder2.build();
      System.out.println(authorizationPolicy.getSpec().getAction());
    }
  }
}
