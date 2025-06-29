import React, { useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import { useProperty } from '../contexts/PropertyContext';
import { FaBed, FaBath, FaUsers, FaMapMarkerAlt, FaHome, FaPhone, FaEnvelope, FaArrowLeft, FaHeart } from 'react-icons/fa';

const PropertyDetail = () => {
  const { id } = useParams();
  const { getPropertyById, loading } = useProperty();
  const property = getPropertyById(id);
  const [contactModal, setContactModal] = useState(false);
  const [favorited, setFavorited] = useState(false);

  if (loading) {
    return (
      <div className="container py-8">
        <div className="loading">
          <div className="spinner"></div>
        </div>
      </div>
    );
  }

  if (!property) {
    return (
      <div className="container py-8">
        <div className="empty-state">
          <FaHome size={64} className="text-gray-300 mx-auto mb-4" />
          <h3 className="text-xl font-semibold text-gray-900 mb-2">
            Imóvel não encontrado
          </h3>
          <p className="text-gray-600 mb-6">
            O imóvel que você está procurando não existe ou foi removido.
          </p>
          <Link to="/properties" className="btn btn-primary">
            <FaArrowLeft />
            Voltar para Imóveis
          </Link>
        </div>
      </div>
    );
  }

  const handleContact = () => {
    setContactModal(true);
  };

  const handleCall = () => {
    // Simular chamada telefônica
    alert(`Ligando para ${property.proprietario.nome} ${property.proprietario.sobrenome}`);
  };

  const handleEmail = () => {
    // Abrir cliente de email
    const subject = `Interesse no imóvel - ${property.tipo}`;
    const body = `Olá ${property.proprietario.nome},\n\nTenho interesse no seu imóvel:\n${property.tipo} - ${property.endereco}\nValor: R$ ${property.valor.toLocaleString('pt-BR')}\n\nAguardo seu retorno.\n\nAtenciosamente,`;
    window.open(`mailto:${property.proprietario.email}?subject=${encodeURIComponent(subject)}&body=${encodeURIComponent(body)}`);
  };

  const handleFavorite = () => {
    setFavorited(!favorited);
    alert(favorited ? 'Removido dos favoritos' : 'Adicionado aos favoritos');
  };

  const PropertyFeature = ({ icon: Icon, label, value }) => (
    <div className="flex items-center gap-2 text-gray-600">
      <Icon className="text-blue-600" />
      <span>{label}: {value}</span>
    </div>
  );

  const PropertySection = ({ title, children }) => (
    <div className="card">
      <h3 className="text-lg font-semibold mb-4">{title}</h3>
      {children}
    </div>
  );

  return (
    <div className="container py-8">
      {/* Breadcrumb */}
      <div className="mb-6">
        <Link to="/properties" className="text-blue-600 hover:text-blue-700 flex items-center gap-2">
          <FaArrowLeft />
          Voltar para Imóveis
        </Link>
      </div>

      <div className="grid grid-2 gap-8">
        {/* Main Content */}
        <div>
          {/* Image */}
          <div className="card p-0 overflow-hidden mb-6">
            <img 
              src={property.imagem} 
              alt={property.tipo}
              className="w-full h-96 object-cover"
            />
          </div>

          {/* Basic Info */}
          <PropertySection title="Informações Básicas">
            <div className="grid grid-2 gap-4">
              <PropertyFeature icon={FaHome} label="Tipo" value={property.tipo} />
              <PropertyFeature icon={FaMapMarkerAlt} label="Endereço" value={property.endereco} />
              <PropertyFeature icon={FaBath} label="Banheiros" value={property.banheiros} />
              <PropertyFeature icon={FaUsers} label="Capacidade" value={`${property.maxInquilinos} pessoas`} />
              <PropertyFeature icon={FaMapMarkerAlt} label="Área" value={`${property.area}m²`} />
              {property.compartilhado && (
                <PropertyFeature icon={FaUsers} label="Compartilhamento" value={property.tipoCompartilhamento} />
              )}
            </div>
          </PropertySection>

          {/* Specific Features */}
          <PropertySection title="Características Específicas">
            <div className="space-y-3">
              {property.tipo === 'Casa' && (
                <>
                  <PropertyFeature icon={FaBed} label="Quartos" value={property.quartos} />
                  <PropertyFeature icon={FaHome} label="Vagas de Garagem" value={property.garagem} />
                </>
              )}
              
              {property.tipo === 'República' && (
                <>
                  <PropertyFeature icon={FaBed} label="Quartos" value={property.quartos} />
                  <div className="flex items-center gap-2 text-gray-600">
                    <FaHome className="text-blue-600" />
                    <span>Cozinha compartilhada: {property.cozinhaCompartilhada ? 'Sim' : 'Não'}</span>
                  </div>
                  <div className="flex items-center gap-2 text-gray-600">
                    <FaHome className="text-blue-600" />
                    <span>Internet: {property.internet ? 'Sim' : 'Não'}</span>
                  </div>
                </>
              )}
              
              {property.tipo === 'Pensionato' && (
                <>
                  <PropertyFeature icon={FaBed} label="Quartos" value={property.quartos} />
                  <div className="flex items-center gap-2 text-gray-600">
                    <FaHome className="text-blue-600" />
                    <span>Refeições inclusas: {property.refeicoesInclusas ? 'Sim' : 'Não'}</span>
                  </div>
                  <div className="flex items-center gap-2 text-gray-600">
                    <FaHome className="text-blue-600" />
                    <span>Limpeza inclusa: {property.limpezaInclusa ? 'Sim' : 'Não'}</span>
                  </div>
                  <div className="flex items-center gap-2 text-gray-600">
                    <FaHome className="text-blue-600" />
                    <span>Horário de fechamento: {property.horarioFechamento ? 'Sim' : 'Não'}</span>
                  </div>
                  <div className="flex items-center gap-2 text-gray-600">
                    <FaHome className="text-blue-600" />
                    <span>Regras rigorosas: {property.regrasRigorosas ? 'Sim' : 'Não'}</span>
                  </div>
                </>
              )}
              
              {property.tipo === 'Kitnet' && (
                <>
                  <div className="flex items-center gap-2 text-gray-600">
                    <FaHome className="text-blue-600" />
                    <span>Cozinha: {property.cozinha ? 'Sim' : 'Não'}</span>
                  </div>
                  <div className="flex items-center gap-2 text-gray-600">
                    <FaHome className="text-blue-600" />
                    <span>Sacada: {property.sacada ? 'Sim' : 'Não'}</span>
                  </div>
                </>
              )}
              
              {property.tipo === 'Quarto' && (
                <>
                  <div className="flex items-center gap-2 text-gray-600">
                    <FaBath className="text-blue-600" />
                    <span>Banheiro privativo: {property.banheiroPrivativo ? 'Sim' : 'Não'}</span>
                  </div>
                  <div className="flex items-center gap-2 text-gray-600">
                    <FaHome className="text-blue-600" />
                    <span>Ar condicionado: {property.arCondicionado ? 'Sim' : 'Não'}</span>
                  </div>
                </>
              )}
              
              {property.tipo === 'Prédio de Kitnets' && (
                <>
                  <PropertyFeature icon={FaHome} label="Andares" value={property.andares} />
                  <PropertyFeature icon={FaHome} label="Kitnets" value={property.kitnets} />
                </>
              )}
            </div>
          </PropertySection>

          {/* Description */}
          <PropertySection title="Descrição">
            <p className="text-gray-600 leading-relaxed">
              Este {property.tipo.toLowerCase()} está localizado em {property.endereco} e oferece 
              uma excelente oportunidade para quem busca moradia. Com {property.area}m² de área, 
              {property.banheiros} banheiro{property.banheiros !== 1 ? 's' : ''} e capacidade para 
              {property.maxInquilinos} pessoa{property.maxInquilinos !== 1 ? 's' : ''}, este imóvel 
              atende às necessidades de diversos perfis de moradores.
            </p>
          </PropertySection>
        </div>

        {/* Sidebar */}
        <div className="space-y-6">
          {/* Price Card */}
          <div className="card bg-gradient-to-r from-blue-500 to-blue-600 text-white">
            <div className="text-center">
              <p className="text-sm opacity-90 mb-2">Valor do Aluguel</p>
              <p className="text-4xl font-bold mb-2">
                R$ {property.valor.toLocaleString('pt-BR')}
              </p>
              <p className="text-sm opacity-90">por mês</p>
            </div>
            <div className="mt-6 space-y-3">
              <button 
                onClick={handleContact}
                className="btn bg-white text-blue-600 hover:bg-gray-100 w-full transition-colors duration-200"
                type="button"
              >
                <FaPhone />
                Entrar em Contato
              </button>
              <button 
                onClick={handleFavorite}
                className={`btn btn-outline border-white text-white hover:bg-white hover:text-blue-600 w-full transition-colors duration-200 ${favorited ? 'bg-white text-blue-600' : ''}`}
                type="button"
              >
                <FaHeart />
                {favorited ? 'Favoritado' : 'Favoritar'}
              </button>
            </div>
          </div>

          {/* Owner Info */}
          <PropertySection title="Proprietário">
            <div className="space-y-3">
              <div>
                <p className="font-semibold text-gray-900">
                  {property.proprietario.nome} {property.proprietario.sobrenome}
                </p>
                <p className="text-gray-600">{property.proprietario.email}</p>
              </div>
              <div className="space-y-2">
                <button 
                  onClick={handleCall}
                  className="btn btn-outline w-full transition-colors duration-200"
                  type="button"
                >
                  <FaPhone />
                  Ligar
                </button>
                <button 
                  onClick={handleEmail}
                  className="btn btn-outline w-full transition-colors duration-200"
                  type="button"
                >
                  <FaEnvelope />
                  Enviar Email
                </button>
              </div>
            </div>
          </PropertySection>

          {/* Similar Properties */}
          <PropertySection title="Imóveis Similares">
            <p className="text-gray-600 text-sm">
              Em breve: outros imóveis similares nesta região
            </p>
          </PropertySection>
        </div>
      </div>

      {/* Contact Modal */}
      {contactModal && (
        <div className="modal-overlay">
          <div className="modal-content">
            <div className="modal-header">
              <h3 className="modal-title">Entrar em Contato</h3>
              <button
                onClick={() => setContactModal(false)}
                className="modal-close"
                type="button"
              >
                ×
              </button>
            </div>
            <div className="mb-6">
              <p className="text-gray-600 mb-4">
                Escolha como deseja entrar em contato com o proprietário:
              </p>
              <div className="space-y-3">
                <button
                  onClick={handleCall}
                  className="btn btn-primary w-full"
                  type="button"
                >
                  <FaPhone />
                  Ligar por Telefone
                </button>
                <button
                  onClick={handleEmail}
                  className="btn btn-outline w-full"
                  type="button"
                >
                  <FaEnvelope />
                  Enviar Email
                </button>
              </div>
            </div>
            <div className="flex justify-end">
              <button
                onClick={() => setContactModal(false)}
                className="btn btn-secondary"
                type="button"
              >
                Cancelar
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default PropertyDetail; 