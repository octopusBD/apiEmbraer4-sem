<template>
    <div id="footer">
            <div class="nav">
              <a href="https://github.com/octopusBD/apiEmbraer4-sem">OctopusBD &copy; {{ new Date().getFullYear() }} - v.0.2_22-03-23</a>
              </div>
            </div>
  </template>
  

  
  <script>
    export default {
      data: () => ({
        icons: [
          'mdi-github',
        ],
      }),
    }
  </script>

  <style>
  @media only screen and (max-width: 600px) {
  .bottom-nav {
    flex-direction: column;
  }
} 
.nav{
        font-size: 15px;

    }

    #footer {
    height: 30px;
    background-color: #333333;
    color: white;
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100%;
    padding:0%
    }

    a{
        color:white;
        text-decoration: none;
        font-size: 13px;
    }
  </style>