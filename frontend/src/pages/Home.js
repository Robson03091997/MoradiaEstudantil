import React from 'react';
import { Link } from 'react-router-dom';
import { useProperty } from '../contexts/PropertyContext';
import { FaHome, FaSearch, FaMapMarkerAlt, FaBed, FaUsers } from 'react-icons/fa';

const Home = () => {
  const { properties } = useProperty();

  // Pegar alguns imóveis para mostrar na página inicial
  const featuredProperties = properties.slice(0, 6);

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
      </div>
    </Link>
  );

  return (
    <div>
      {/* Hero Section */}
      <section className="bg-gradient-to-r from-blue-600 to-blue-800 text-white py-20">
        <div className="container">
          <div className="text-center max-w-4xl mx-auto">
            <h1 className="text-5xl font-bold mb-6">
              Encontre o Lar Perfeito
            </h1>
            <p className="text-xl mb-8 opacity-90">
              Milhares de imóveis disponíveis para locação. Desde casas até quartos compartilhados, 
              temos opções para todos os perfis e orçamentos.
            </p>
            <div className="flex flex-col sm:flex-row gap-4 justify-center">
              <Link to="/properties" className="btn bg-white text-blue-600 hover:bg-gray-100">
                <FaSearch />
                Ver Imóveis
              </Link>
              <Link to="/register" className="btn btn-outline border-white text-white hover:bg-white hover:text-blue-600">
                <FaHome />
                Anunciar Imóvel
              </Link>
            </div>
          </div>
        </div>
      </section>

      {/* Features Section */}
      <section className="features-section">
        <div className="container">
          <div className="features-header">
            <h2 className="features-title">
              Por que escolher nossa plataforma?
            </h2>
            <p className="features-subtitle">
              Oferecemos uma experiência completa para encontrar ou anunciar imóveis, 
              com foco em transparência e facilidade de uso.
            </p>
          </div>

          <div className="features-grid">
            <div className="feature-card">
              <div className="feature-icon-wrapper feature-icon-search">
                <FaSearch className="feature-icon" />
              </div>
              <div className="feature-content">
                <h3 className="feature-title">Busca Inteligente</h3>
                <p className="feature-description">
                  Filtros avançados para encontrar exatamente o que você procura, 
                  com base em localização, preço e características.
                </p>
              </div>
            </div>

            <div className="feature-card">
              <div className="feature-icon-wrapper feature-icon-home">
                <FaHome className="feature-icon" />
              </div>
              <div className="feature-content">
                <h3 className="feature-title">Variedade de Opções</h3>
                <p className="feature-description">
                  Desde casas completas até quartos compartilhados, 
                  oferecemos opções para todos os tipos de moradores.
                </p>
              </div>
            </div>

            <div className="feature-card">
              <div className="feature-icon-wrapper feature-icon-community">
                <FaUsers className="feature-icon" />
              </div>
              <div className="feature-content">
                <h3 className="feature-title">Comunidade Ativa</h3>
                <p className="feature-description">
                  Conectamos proprietários e inquilinos de forma segura, 
                  facilitando o processo de locação.
                </p>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Featured Properties */}
      <section className="py-16">
        <div className="container">
          <div className="flex items-center justify-between mb-8">
            <div>
              <h2 className="text-3xl font-bold text-gray-900 mb-2">
                Imóveis em Destaque
              </h2>
              <p className="text-gray-600">
                Confira algumas das melhores opções disponíveis
              </p>
            </div>
            <Link to="/properties" className="btn btn-outline">
              Ver Todos
            </Link>
          </div>

          {featuredProperties.length > 0 ? (
            <div className="grid grid-3 gap-6">
              {featuredProperties.map(property => (
                <PropertyCard key={property.id} property={property} />
              ))}
            </div>
          ) : (
            <div className="empty-state">
              <FaHome size={64} className="text-gray-300 mx-auto mb-4" />
              <h3 className="text-xl font-semibold text-gray-900 mb-2">
                Nenhum imóvel disponível no momento
              </h3>
              <p className="text-gray-600 mb-6">
                Em breve teremos imóveis incríveis para você!
              </p>
            </div>
          )}
        </div>
      </section>

      {/* CTA Section */}
      <section className="bg-gray-900 text-white py-16">
        <div className="container">
          <div className="text-center max-w-3xl mx-auto">
            <h2 className="text-3xl font-bold mb-4">
              Pronto para encontrar seu novo lar?
            </h2>
            <p className="text-xl mb-8 opacity-90">
              Junte-se a milhares de pessoas que já encontraram o lugar perfeito para morar.
            </p>
            <div className="flex flex-col sm:flex-row gap-4 justify-center">
              <Link to="/properties" className="btn bg-blue-600 hover:bg-blue-700">
                <FaSearch />
                Explorar Imóveis
              </Link>
              <Link to="/register" className="btn btn-outline border-white text-white hover:bg-white hover:text-gray-900">
                <FaHome />
                Começar Agora
              </Link>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
};

export default Home; 