<template>
    <div class="topnav" id="myTopnav">
        <a class="logo"/>
        <a>Mac</a>
        <a>aPad</a>
        <a>aPhone</a>
        <a>Watch</a>
        <a>TV</a>
        <a>Music</a>
        <a href="/" class="active">Support</a>
        <a><v-icon style="color: #f5f5f7">search</v-icon></a>
    </div>

</template>


<script>

</script>

<style>
    /* Add a black background color to the top navigation */
    .topnav {
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        background-color: #312E2F;
        overflow: hidden;
    }

/* Style the links inside the navigation bar */
    .topnav a {
        display: flex;
        flex-direction: column;
        opacity: 70%;
        color: white !important;
        text-align: center;
        padding: 8px;
        text-decoration: none;
        font-size: 18px;
        width: 100px;
        transition: opacity 200ms;
    }

    /* Add an active class to highlight the current page */
    .active {
        opacity: 100% !important;
    }

    /* Hide the link that should open and close the topnav on small screens */
    .topnav .icon {
        display: none;
    }

/* Dropdown container - needed to position the dropdown content */
.dropdown {
  float: left;
  overflow: hidden;
}

/* Style the dropdown button to fit inside the topnav */
.dropdown .dropbtn {
  font-size: 17px;
  border: none;
  outline: none;
  color: white;
  padding: 14px 16px;
  background-color: inherit;
  font-family: inherit;
  margin: 0;
}

/* Style the dropdown content (hidden by default) */
.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

/* Style the links inside the dropdown */
.dropdown-content a {
  float: none;
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  text-align: left;
}

.logo {
    height: 20px;
    background-image: url("../assets/acornlogo.svg");
    background-size: contain;
    background-position: center;
    background-repeat: no-repeat;
}

/* Add a dark background on topnav links and the dropdown button on hover */
    .topnav a:hover, .dropdown:hover .dropbtn {
        opacity: 100%;
    }

/* Add a grey background to dropdown links on hover */
.dropdown-content a:hover {
  background-color: #312E2F;
  color: black;
}

/* Show the dropdown menu when the user moves the mouse over the dropdown button */
.dropdown:hover .dropdown-content {
  display: block;
}


/* When the screen is less than 600 pixels wide, hide all links, except for the first one ("Home"). Show the link that contains should open and close the topnav (.icon) */
@media screen and (max-width: 600px) {
  .topnav a:not(:first-child), .dropdown .dropbtn {
    display: none;
  }
  .topnav a.icon {
    float: right;
    display: block;
  }


}

/* The "responsive" class is added to the topnav with JavaScript when the user clicks on the icon. This class makes the topnav look good on small screens (display the links vertically instead of horizontally) */
@media screen and (max-width: 600px) {
  .topnav.responsive {position: relative;}
  .topnav.responsive a.icon {
    position: absolute;
    right: 0;
    top: 0;
  }
  .topnav.responsive a {
    float: none;
    display: block;
    text-align: left;
  }
  .topnav.responsive .dropdown {float: none;}
  .topnav.responsive .dropdown-content {position: relative;}
  .topnav.responsive .dropdown .dropbtn {
    display: block;
    width: 100%;
    text-align: left;
  }
}
</style>





