package io.fabric8.testing.istio;

import io.fabric8.istio.api.telemetry.v1alpha1.Telemetry;
import io.fabric8.istio.api.telemetry.v1alpha1.TelemetryList;
import io.fabric8.istio.client.IstioClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;

public class TelemetryCreate {
  public static void main(String[] args) {
    try (KubernetesClient kubernetesClient = new KubernetesClientBuilder().build()) {
      IstioClient istioClient = kubernetesClient.adapt(IstioClient.class);
      Telemetry wasmPlugin = kubernetesClient.getKubernetesSerialization().unmarshal(TelemetryCreate.class.getResourceAsStream("/telemetry-v1alpha1.json"), Telemetry.class);

      istioClient.v1beta1().resources(Telemetry.class, TelemetryList.class).resource(wasmPlugin).create();
      System.out.println("Successfully created Telemetry!");
    }
  }
}
