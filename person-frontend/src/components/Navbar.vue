<template>
  <nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Notizblock</a>
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
