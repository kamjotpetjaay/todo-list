<template>
  <header>
    <nav class="navbar navbar-dark bg-dark navbar-expand-lg">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">Todo App</a>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <router-link to="/">Tareas</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/about">Acerca de</router-link>
            </li>
          </ul>
        </div>
      </div>
      <div class="nav-space-left"></div>
    </nav>
  </header>
</template>

<script>
export default {
  name: 'NavBar'
}
</script>

<style>
nav ul {
  display: flex;
  gap: 20px;
}

.navbar-nav,
.mr-auto {
  flex: 1;
  margin: auto !important;
  display: flex;
  justify-content: space-evenly;
}

.container-fluid {
  width: 30%;
}

.nav-space-left {
  width: 70%;
}
</style>
