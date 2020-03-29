<template>
    <div class="grid-container">
        <div class="grid-item">
            <ProductCard></ProductCard>
        </div>
        <div class="grid-item">
             <ProductCard></ProductCard>
        </div>
        <!-- <div class="grid-item">
             <ProductCard></ProductCard>
        </div>
         <div class="grid-item">
             <ProductCard></ProductCard>
        </div>
         <div class="grid-item">
             <ProductCard></ProductCard>
        </div>
         <div class="grid-item">
             <ProductCard></ProductCard>
        </div>
        <div class="grid-item">
             <ProductCard></ProductCard>
        </div>
         <div class="grid-item">
             <ProductCard></ProductCard>
        </div>
        <div class="grid-item">
             <ProductCard></ProductCard>
        </div>
        
    </div> -->
</template>

<script>
import ProductCard from './ProductCard' // this is the popup table
export default {
    components:{ProductCard},
    
}
</script>


<style>
.grid-container {
  display: grid;
  grid-template-columns: auto auto auto;
  background-color: #2196F3;
  padding: 10px;
}
/* .grid-item {
  background-color: rgba(255, 255, 255, 0.8);
  border: 1px solid rgba(0, 0, 0, 0.8);
  padding: 20px;
  font-size: 30px;
  text-align: center;
} */
</style>


