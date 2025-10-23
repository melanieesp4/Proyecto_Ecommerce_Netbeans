// Obtener referencias a los elementos del DOM
const cantidadInput = document.getElementById('cantidad');
const btnMenos = document.querySelector('.btn-menos');
const btnMas = document.querySelector('.btn-mas');
const elementoPrecioTotal = document.querySelector('.precio-total');

// Función para actualizar el precio total
function actualizarPrecioTotal() {
  const cantidad = parseInt(cantidadInput.value);
  const precioUnitario = 159.99;
  const precioTotal = cantidad * precioUnitario;
  elementoPrecioTotal.textContent = `US$${precioTotal.toFixed(2)}`;
}

// Agregar eventos para los botones de cantidad
btnMenos.addEventListener('click', () => {
  if (cantidadInput.value > 1) {
    cantidadInput.value = parseInt(cantidadInput.value) - 1;
    actualizarPrecioTotal();
  }
});

btnMas.addEventListener('click', () => {
  cantidadInput.value = parseInt(cantidadInput.value) + 1;
  actualizarPrecioTotal();
});

// Actualizar el precio total al cargar la página
actualizarPrecioTotal();
