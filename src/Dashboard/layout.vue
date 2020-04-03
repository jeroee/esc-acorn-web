<template>
  <div class="wrapper">
    <div class="main-panel">
      <Try></Try>
      <ProductCard></ProductCard>
    </div>

  </div>
</template>

<script>
import Try from "../views/Try.vue";
import ProductCard from "../components/ProductCard";


export default {
  components: {
    Try,
    ProductCard,
  },
 
  }
</script>


