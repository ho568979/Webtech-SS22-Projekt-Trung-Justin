<template>
  <nav class="navbar navbar-expand-lg bg-light">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <div class="container-fluid">
      <h1 class="logo">
        <a class="navbar-brand" href="/"><span class="text-primary"><i class="fas fa-book-open"></i> Notiz</span>block</a>
      </h1>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <router-link class="nav-link" to="/">Home</router-link>
          <router-link class="nav-link" to="/account">Account</router-link>
          <router-link class="nav-link" to="/about">About</router-link>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: 'NavbarItem'
}
</script>

<style scoped>

</style>
