<template>        
<div id="navbar">
    <h1>I'm FirstPage!</h1>
  <navigation></navigation>
</div>
</template>



<script>
Vue.component("navigation", {
  props: ["item"],
  data() {
    return {
      isOpen: false,
      active: false,
      navList: [
      { url: "#", name: "About Us" },
      { url: "#", name: "Story" },
      { url: "#", name: "Price" },
      {
        url: "#",
        name: "Services",
        children: [
        {
          url: "https://twitter.com/andrejsharapov",
          name: "Twitter",
          target: "_blank" },

        {
          url: "https://dribbble.com/andrejsharapov",
          name: "Dribbble",
          target: "_blank" },

        {
          url: "https://www.behance.net/andrejsharapov",
          name: "Behance",
          target: "_blank" },

        {
          url: "https://www.instagram.com/andrej.sharapov/",
          name: "Instagram",
          target: "_blank" }] },



      { url: "#", name: "Testimonials" },
      { url: "#", name: "Contact Us" }] };


  },
  template: `
    <ul id="navigation">
        <li v-for="item in navList">
            <template v-if="item.children">
                <a 
                  :href="item.url" 
                  :title="item.name" 
                  @click="isOpen = !isOpen, active = !active" 
                  :class="{ active }">{{ item.name }} <svg viewBox="0 0 451.847 451.847" width="12"><path d="M225.923,354.706c-8.098,0-16.195-3.092-22.369-9.263L9.27,151.157c-12.359-12.359-12.359-32.397,0-44.751
		c12.354-12.354,32.388-12.354,44.748,0l171.905,171.915l171.906-171.909c12.359-12.354,32.391-12.354,44.744,0
		c12.365,12.354,12.365,32.392,0,44.751L248.292,345.449C242.115,351.621,234.018,354.706,225.923,354.706z" fill="#fff"/></svg></a>
                <div :class="{ isOpen }" class="dropdown">
                    <ul>
                        <li 
                          v-for="{ url, name, index, target } in item.children" 
                          :key="index">
                            <a :href="url" :title="name" :target="target">{{ name }}
                        </li>
                    </ul>
                </div>
            </template>
            <template v-else>
                <a 
                  :href="item.url" 
                  :title="item.name">{{ item.name }}</a>
            </template>
        </li>
    </ul>
    ` });


new Vue({ el: "#navbar" });
</script>

<style>
@charset "UTF-8";
@import url("https://fonts.googleapis.com/css?family=Raleway&display=swap");
*,
::before,
::after {
  box-sizing: border-box;
  padding: 0;
  margin: 0;
}

ul {
  list-style-type: none;
}
ul li {
  position: relative;
  margin: 0.25em;
  text-align: center;
}
ul li a {
  display: block;
  padding: 0.55em 1em;
  text-decoration: none;
  color: #d9d9d9;
  border-radius: 0.35em;
  background-color: rgba(33, 37, 41, 0.25);
  transition: all 200ms ease;
}
ul li a:hover {
  color: #ffffff;
  background-color: rgba(33, 37, 41, 0.65);
}
ul li a.active {
  background-color: rgba(33, 37, 41, 0.45);
}
ul li ul li a {
  background-color: transparent;
}
ul#navigation {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: flex-start;
}

.dropdown {
  position: absolute;
  left: 50%;
  transform: translatex(-50%) rotatex(90deg) scale(0);
  margin-top: 0.55em;
  transform-origin: 0 0;
  border-radius: 0.35em;
  background-color: rgba(33, 37, 41, 0.15);
  visibility: hidden;
  opacity: 0;
  transition: all 200ms linear;
}
.dropdown.isOpen {
  transform: translatex(-50%);
  visibility: visible;
  opacity: 1;
}

body {
  position: relative;
  overflow: hidden;
  padding-top: 4em;
  padding-bottom: 4em;
  min-height: 100vh;
  font-family: "Raleway", sans-serif;
  font-size: 16px;
  font-weight: 500;
  line-height: 1.5;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: flex-start;
  color: currentcolor;
  background: radial-gradient(#d9d9d9 6%, transparent 6%), #961313;
  background-position: 0 0, 5px 5px;
  background-size: 5px 5px;
}
body::before {
  position: absolute;
  content: '😍';
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: #ffffff;
  opacity: .1;
  font-size: 30vw;
  animation: emoji 20s linear 3s infinite;
}

@keyframes emoji {
  1% {
    content: '😍';
  }
  2% {
    content: '😙';
  }
  3% {
    content: '😌';
  }
  4% {
    content: '😋';
  }
  5% {
    content: '😉';
  }
  6% {
    content: '😁';
  }
  7% {
    content: '😜';
  }
  8% {
    content: '😍';
  }
  9% {
    content: '😝';
  }
  10% {
    content: '😊';
  }
  11%,
  100% {
    content: '';
  }
}

</style>
