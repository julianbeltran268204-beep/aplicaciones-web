/**
 * ============================================================
 *  templates.js — El HTML de cada tarjeta del dashboard
 * ============================================================
 *
 * El HTML ya está listo. Lo que falta es INYECTAR LOS DATOS:
 * cambia cada texto en MAYÚSCULAS por el dato real usando ${ }.
 *
 *   Ejemplo:   <h3 class="character-name">NOMBRE</h3>
 *          →   <h3 class="character-name">${char.name}</h3>
 *
 * Un personaje de la API se ve así:
 *   { id: 1, name: "Rick Sanchez", status: "Alive", image: "https://..." }
 */

/**
 * Tarjeta de un personaje.
 * Se inyecta dentro de un <div class="character-card"> en la grilla.
 *
 * TODO: Cambia los textos en MAYÚSCULAS:
 *         IMAGEN        → char.image
 *         NOMBRE        → char.name (2 lugares)
 *         ESTADO        → char.status
 *         ESTADO_CLASE  → char.status.toLowerCase()   (alive, dead o unknown: así toma el color del CSS)
 *         ID            → char.id
 *         CLASE_ACTIVA  → isFav ? 'active' : ''
 *         TEXTO_BOTON   → isFav ? 'En Favoritos' : 'Añadir a Favoritos'
 *
 * @param {Object} char - El personaje
 * @param {boolean} isFav - ¿Ya está en favoritos?
 */
export const characterCardTemplate = (char, isFav) => `
    <div class="card-image-wrapper">
        <img src="${char.image}" alt="${char.name}" loading="lazy">
    </div>
    <div class="card-content">
        <h3 class="character-name">${char.name}</h3>
        <div class="status-label status-${char.status.toLowerCase()}">
            <span class="status-indicator"></span>
            ${char.status}
        </div>
        <button class="fav-btn ${isFav ? 'active' : ''}" data-id="${char.id}">
            <i data-lucide="star"></i>
            <span>${isFav ? 'En Favoritos' : 'Añadir a Favoritos'}</span>
        </button>
    </div>
`;

export const favoriteItemTemplate = (fav) => `
    <img src="${fav.image}" alt="${fav.name}">
    <div class="fav-item-info">
        <p class="fav-item-name">${fav.name}</p>
    </div>
    <button class="remove-fav" data-id="${fav.id}">
        <i data-lucide="x"></i>
    </button>
`;

export const emptyFavoritesTemplate = () => `
    <div class="empty-favorites">
        <p>No favorites yet</p>
    </div>
`;