<template>
  <link rel="stylesheet" href="CSS/utilities.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
  <body id="home">
    <div class="content">
      <h1>Dein Online-Notizblock</h1>
      <p>Alle deine Notizen an einem Platz</p>
      <router-link class="btn" to="/account"><i class="fas fa-chevron-right"></i>Anmelden</router-link>
    </div>
  </body>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
