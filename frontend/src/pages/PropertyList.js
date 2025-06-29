import React, { useState, useEffect, useCallback } from 'react';
import { Link, useSearchParams } from 'react-router-dom';
import { useProperty } from '../contexts/PropertyContext';
import { FaSearch, FaFilter, FaBed, FaUsers, FaMapMarkerAlt } from 'react-icons/fa';

const PropertyList = () => {
  const { properties, loading } = useProperty();
  const [searchParams, setSearchParams] = useSearchParams();
  const [filteredProperties, setFilteredProperties] = useState([]);
  const [filters, setFilters] = useState({
    search: searchParams.get('busca') || '',
    tipo: searchParams.get('tipo') || '',
    minPrice: '',
    maxPrice: '',
    sortBy: 'relevance'
  });

  const applyFilters = useCallback(() => {
    let filtered = [...properties];

    // Filtro por busca (endereço)
    if (filters.search) {
      filtered = filtered.filter(property =>
        property.endereco.toLowerCase().includes(filters.search.toLowerCase()) ||
        property.tipo.toLowerCase().includes(filters.search.toLowerCase())
      );
    }

    // Filtro por tipo
    if (filters.tipo) {
      filtered = filtered.filter(property => property.tipo === filters.tipo);
    }

    // Filtro por preço mínimo
    if (filters.minPrice) {
      filtered = filtered.filter(property => property.valor >= parseFloat(filters.minPrice));
    }

    // Filtro por preço máximo
    if (filters.maxPrice) {
      filtered = filtered.filter(property => property.valor <= parseFloat(filters.maxPrice));
    }

    // Ordenação
    switch (filters.sortBy) {
      case 'price-asc':
        filtered.sort((a, b) => a.valor - b.valor);
        break;
      case 'price-desc':
        filtered.sort((a, b) => b.valor - a.valor);
        break;
      case 'area-asc':
        filtered.sort((a, b) => a.area - b.area);
        break;
      case 'area-desc':
        filtered.sort((a, b) => b.area - a.area);
        break;
      default:
        // Ordenação por relevância (mantém ordem original)
        break;
    }

    setFilteredProperties(filtered);
  }, [properties, filters]);

  useEffect(() => {
    applyFilters();
  }, [applyFilters]);

  const handleFilterChange = (key, value) => {
    setFilters(prev => ({ ...prev, [key]: value }));
    
    // Atualizar URL params
    const newParams = new URLSearchParams(searchParams);
    if (value) {
      newParams.set(key, value);
    } else {
      newParams.delete(key);
    }
    setSearchParams(newParams);
  };

  const clearFilters = () => {
    setFilters({
      search: '',
      tipo: '',
      minPrice: '',
      maxPrice: '',
      sortBy: 'relevance'
    });
    setSearchParams({});
  };

  const PropertyCard = ({ property }) => (
    <Link to={`/properties/${property.id}`} className="card property-card">
      <img 
        src={property.imagem} 
        alt={property.tipo}
        className="property-image"
      />
      <div className="property-info">
        <div className="flex items-center justify-between mb-2">
          <span className="badge badge-primary">{property.tipo}</span>
          {property.compartilhado && (
            <span className="badge badge-warning">Compartilhado</span>
          )}
        </div>
        <h3 className="property-title">{property.tipo} - {property.endereco.split(',')[0]}</h3>
        <p className="property-address">{property.endereco}</p>
        <div className="property-price">R$ {property.valor.toLocaleString('pt-BR')}</div>
        <div className="property-features">
          <span className="property-feature">
            <FaBed /> {property.quartos || 1} quarto{property.quartos !== 1 ? 's' : ''}
          </span>
          <span className="property-feature">
            <FaUsers /> {property.maxInquilinos} pessoa{property.maxInquilinos !== 1 ? 's' : ''}
          </span>
          <span className="property-feature">
            <FaMapMarkerAlt /> {property.area}m²
          </span>
        </div>
        <div className="mt-3 text-sm text-gray-500">
          Proprietário: {property.proprietario.nome} {property.proprietario.sobrenome}
        </div>
      </div>
    </Link>
  );

  return (
    <div className="container py-8">
      {/* Header */}
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-gray-900 mb-2">
          Imóveis Disponíveis
        </h1>
        <p className="text-gray-600">
          Encontre o lugar perfeito para morar
        </p>
      </div>

      {/* Filters */}
      <div className="card mb-8">
        <div className="flex items-center gap-4 mb-4">
          <FaFilter className="text-blue-600" />
          <h2 className="text-lg font-semibold">Filtros</h2>
        </div>
        
        <div className="grid grid-2 gap-4 mb-4">
          <div className="form-group">
            <label className="form-label">Buscar</label>
            <div className="relative">
              <FaSearch className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400" />
              <input
                type="text"
                placeholder="Endereço ou tipo de imóvel..."
                value={filters.search}
                onChange={(e) => handleFilterChange('search', e.target.value)}
                className="form-input pl-10"
              />
            </div>
          </div>

          <div className="form-group">
            <label className="form-label">Tipo de Imóvel</label>
            <select
              value={filters.tipo}
              onChange={(e) => handleFilterChange('tipo', e.target.value)}
              className="form-select"
            >
              <option value="">Todos os tipos</option>
              <option value="Casa">Casa</option>
              <option value="Kitnet">Kitnet</option>
              <option value="Quarto">Quarto</option>
              <option value="República">República</option>
              <option value="Pensionato">Pensionato</option>
              <option value="Edícula">Edícula</option>
              <option value="Prédio de Kitnets">Prédio de Kitnets</option>
            </select>
          </div>

          <div className="form-group">
            <label className="form-label">Preço Mínimo (R$)</label>
            <input
              type="number"
              placeholder="0"
              value={filters.minPrice}
              onChange={(e) => handleFilterChange('minPrice', e.target.value)}
              className="form-input"
            />
          </div>

          <div className="form-group">
            <label className="form-label">Preço Máximo (R$)</label>
            <input
              type="number"
              placeholder="5000"
              value={filters.maxPrice}
              onChange={(e) => handleFilterChange('maxPrice', e.target.value)}
              className="form-input"
            />
          </div>
        </div>

        <div className="flex items-center justify-between">
          <div className="flex items-center gap-4">
            <label className="form-label">Ordenar por:</label>
            <select
              value={filters.sortBy}
              onChange={(e) => handleFilterChange('sortBy', e.target.value)}
              className="form-select w-auto"
            >
              <option value="relevance">Relevância</option>
              <option value="price-asc">Menor Preço</option>
              <option value="price-desc">Maior Preço</option>
              <option value="area-asc">Menor Área</option>
              <option value="area-desc">Maior Área</option>
            </select>
          </div>
          
          <button
            onClick={clearFilters}
            className="btn btn-outline"
          >
            Limpar Filtros
          </button>
        </div>
      </div>

      {/* Results */}
      <div className="mb-4">
        <div className="flex items-center justify-between">
          <p className="text-gray-600">
            {filteredProperties.length} imóvel{filteredProperties.length !== 1 ? 'is' : ''} encontrado{filteredProperties.length !== 1 ? 's' : ''}
          </p>
        </div>
      </div>

      {loading ? (
        <div className="loading">
          <div className="spinner"></div>
        </div>
      ) : filteredProperties.length > 0 ? (
        <div className="grid grid-3 gap-6">
          {filteredProperties.map(property => (
            <PropertyCard key={property.id} property={property} />
          ))}
        </div>
      ) : (
        <div className="empty-state">
          <FaSearch size={64} className="text-gray-300 mx-auto mb-4" />
          <h3 className="text-xl font-semibold text-gray-900 mb-2">
            Nenhum imóvel encontrado
          </h3>
          <p className="text-gray-600 mb-6">
            Tente ajustar os filtros ou buscar por outros critérios
          </p>
          <button
            onClick={clearFilters}
            className="btn btn-primary"
          >
            Limpar Filtros
          </button>
        </div>
      )}
    </div>
  );
};

export default PropertyList; 