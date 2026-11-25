import { store } from '../state/store.js';
import { getCharacters } from '../core/api.js';
import { characterCardTemplate, favoriteItemTemplate, emptyFavoritesTemplate } from '../templates.js';

// Refresca los iconos de Lucide (si la librería cargó)
const refreshIcons = () => {
    if (window.lucide) {
        lucide.createIcons();
    }
};

export const dashboard = {
    elements: {
        grid: document.getElementById('charactersGrid'),
        favoritesList: document.getElementById('favoritesList'),
        search: document.getElementById('characterSearch'),
        filter: document.getElementById('statusFilter'),
        loader: document.getElementById('loader'),
        noResults: document.getElementById('noResults'),
        prevBtn: document.getElementById('prevPage'),
        nextBtn: document.getElementById('nextPage'),
        pageInfo: document.getElementById('pageInfo')
    },

    // Temporizador para el debounce de la búsqueda
    searchTimeout: null,

    async init() {
        this.setupEventListeners();
        await this.loadCharacters();
        this.renderFavorites();
    },

    setupEventListeners() {
        const { search, filter, prevBtn, nextBtn } = this.elements;

        // Búsqueda con debounce de 500ms
        search.addEventListener('input', (e) => {
            clearTimeout(this.searchTimeout);
            this.searchTimeout = setTimeout(() => {
                store.state.filters.name = e.target.value.trim();
                store.state.currentPage = 1;
                this.loadCharacters();
            }, 500);
        });

        // Filtro por estado
        filter.addEventListener('change', (e) => {
            store.state.filters.status = e.target.value;
            store.state.currentPage = 1;
            this.loadCharacters();
        });

        // Página anterior
        prevBtn.addEventListener('click', () => {
            if (store.state.currentPage > 1) {
                store.state.currentPage--;
                this.loadCharacters();
                window.scrollTo({ top: 0, behavior: 'smooth' });
            }
        });

        // Página siguiente
        nextBtn.addEventListener('click', () => {
            if (store.state.currentPage < store.state.totalPages) {
                store.state.currentPage++;
                this.loadCharacters();
                window.scrollTo({ top: 0, behavior: 'smooth' });
            }
        });
    },

    async loadCharacters() {
        this.showLoader(true);
        this.elements.noResults.classList.add('hidden');

        const data = await getCharacters(store.state.currentPage, store.state.filters);

        if (data.error || !data.results) {
            store.state.characters = [];
            store.state.totalPages = 0;
            this.elements.noResults.classList.remove('hidden');
        } else {
            store.state.characters = data.results;
            store.state.totalPages = data.info.pages;
        }

        this.renderGrid();
        this.updatePagination();
        this.showLoader(false);
    },

    renderGrid() {
        const { grid } = this.elements;

        // Borra solo las tarjetas (el loader vive dentro de la grilla)
        grid.querySelectorAll('.character-card').forEach(card => card.remove());

        store.state.characters.forEach(char => {
            const card = document.createElement('div');
            card.classList.add('character-card');

            const isFav = store.isFavorite(char.id);
            card.innerHTML = characterCardTemplate(char, isFav);

            const favBtn = card.querySelector('.fav-btn');
            favBtn.addEventListener('click', () => {
                const added = store.toggleFavorite(char);
                favBtn.classList.toggle('active', added);
                favBtn.querySelector('span').textContent = added ? 'En Favoritos' : 'Añadir a Favoritos';
                this.renderFavorites();
                refreshIcons();
            });

            grid.appendChild(card);
        });

        refreshIcons();
    },

    renderFavorites() {
        const { favoritesList } = this.elements;

        if (store.state.favorites.length === 0) {
            favoritesList.innerHTML = emptyFavoritesTemplate();
            return;
        }

        favoritesList.innerHTML = '';

        store.state.favorites.forEach(fav => {
            const item = document.createElement('div');
            item.classList.add('fav-item');
            item.innerHTML = favoriteItemTemplate(fav);

            // La "X" quita el favorito y actualiza la tarjeta principal
            item.querySelector('.remove-fav').addEventListener('click', () => {
                store.toggleFavorite(fav);
                this.renderFavorites();
                this.renderGrid();
            });

            favoritesList.appendChild(item);
        });

        refreshIcons();
    },

    updatePagination() {
        const { prevBtn, nextBtn, pageInfo } = this.elements;
        const { currentPage, totalPages } = store.state;

        prevBtn.disabled = currentPage <= 1;
        nextBtn.disabled = currentPage >= totalPages;
        pageInfo.textContent = totalPages > 0
            ? `Página ${currentPage} de ${totalPages}`
            : 'Página 0 de 0';
    },

    showLoader(show) {
        this.elements.loader.classList.toggle('hidden', !show);
    }
};