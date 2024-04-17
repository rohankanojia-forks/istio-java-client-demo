package io.fabric8.testing.istio;

import io.fabric8.istio.api.extensions.v1alpha1.WasmPlugin;
import io.fabric8.istio.api.extensions.v1alpha1.WasmPluginList;
import io.fabric8.istio.client.IstioClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;

public class WasmPluginCreate {
  public static void main(String[] args) {
    try (KubernetesClient kubernetesClient = new KubernetesClientBuilder().build()) {
      IstioClient istioClient = kubernetesClient.adapt(IstioClient.class);
      WasmPlugin wasmPlugin = kubernetesClient.getKubernetesSerialization().unmarshal(WasmPluginCreate.class.getResourceAsStream("/wasmplugin-v1alpha1.json"), WasmPlugin.class);

      istioClient.v1beta1().resources(WasmPlugin.class, WasmPluginList.class).resource(wasmPlugin).create();
      System.out.println("Successfully created WasmPlugin!");
    }
  }
}
