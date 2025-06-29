import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';
import { useProperty } from '../contexts/PropertyContext';
import { FaHome, FaPlus, FaEdit, FaTrash, FaEye } from 'react-icons/fa';

const MyProperties = () => {
  const { user } = useAuth();
  const { getPropertiesByOwner } = useProperty();
  const [selectedProperty, setSelectedProperty] = useState(null);
  const [showDeleteModal, setShowDeleteModal] = useState(false);

  const userProperties = getPropertiesByOwner(user?.id);

  const handleDelete = (property) => {
    setSelectedProperty(property);
    setShowDeleteModal(true);
  };

  const confirmDelete = () => {
    // Aqui você implementaria a lógica de deletar
    console.log('Deletando imóvel:', selectedProperty.id);
    setShowDeleteModal(false);
    setSelectedProperty(null);
  };

  const PropertyCard = ({ property }) => (
    <div className="card">
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
        
        <div className="mt-4 flex gap-2">
          <Link
            to={`/properties/${property.id}`}
            className="btn btn-outline btn-sm"
          >
            <FaEye />
            Ver
          </Link>
          <button className="btn btn-outline btn-sm">
            <FaEdit />
            Editar
          </button>
          <button
            onClick={() => handleDelete(property)}
            className="btn btn-outline btn-sm text-red-600 hover:text-red-700"
          >
            <FaTrash />
            Excluir
          </button>
        </div>
      </div>
    </div>
  );

  return (
    <div className="container py-8">
      {/* Header */}
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-gray-900 mb-2">
          Meus Imóveis
        </h1>
        <p className="text-gray-600">
          Gerencie seus imóveis cadastrados
        </p>
      </div>

      {/* Stats */}
      <div className="grid grid-4 gap-6 mb-8">
        <div className="card text-center">
          <div className="text-2xl font-bold text-blue-600 mb-2">
            {userProperties.length}
          </div>
          <div className="text-gray-600">Total de Imóveis</div>
        </div>
        <div className="card text-center">
          <div className="text-2xl font-bold text-green-600 mb-2">
            R$ {userProperties.reduce((sum, p) => sum + p.valor, 0).toLocaleString('pt-BR')}
          </div>
          <div className="text-gray-600">Valor Total</div>
        </div>
        <div className="card text-center">
          <div className="text-2xl font-bold text-purple-600 mb-2">
            {userProperties.filter(p => p.compartilhado).length}
          </div>
          <div className="text-gray-600">Compartilhados</div>
        </div>
        <div className="card text-center">
          <div className="text-2xl font-bold text-orange-600 mb-2">
            {Math.round(userProperties.reduce((sum, p) => sum + p.valor, 0) / userProperties.length)}
          </div>
          <div className="text-gray-600">Valor Médio</div>
        </div>
      </div>

      {/* Actions */}
      <div className="flex justify-between items-center mb-6">
        <h2 className="text-xl font-semibold">
          Imóveis Cadastrados ({userProperties.length})
        </h2>
        <Link to="/add-property" className="btn btn-primary">
          <FaPlus />
          Anunciar Novo Imóvel
        </Link>
      </div>

      {/* Properties List */}
      {userProperties.length > 0 ? (
        <div className="grid grid-3 gap-6">
          {userProperties.map(property => (
            <PropertyCard key={property.id} property={property} />
          ))}
        </div>
      ) : (
        <div className="empty-state">
          <FaHome size={64} className="text-gray-300 mx-auto mb-4" />
          <h3 className="text-xl font-semibold text-gray-900 mb-2">
            Você ainda não tem imóveis cadastrados
          </h3>
          <p className="text-gray-600 mb-6">
            Comece anunciando seu primeiro imóvel e veja os interessados chegarem!
          </p>
          <Link to="/add-property" className="btn btn-primary">
            <FaPlus />
            Anunciar Primeiro Imóvel
          </Link>
        </div>
      )}

      {/* Delete Modal */}
      {showDeleteModal && (
        <div className="modal-overlay">
          <div className="modal-content">
            <div className="modal-header">
              <h3 className="modal-title">Confirmar Exclusão</h3>
              <button
                onClick={() => setShowDeleteModal(false)}
                className="modal-close"
              >
                ×
              </button>
            </div>
            <div className="mb-6">
              <p className="text-gray-600 mb-4">
                Tem certeza que deseja excluir o imóvel:
              </p>
              <div className="bg-gray-50 p-4 rounded-lg">
                <h4 className="font-semibold">{selectedProperty?.tipo}</h4>
                <p className="text-gray-600">{selectedProperty?.endereco}</p>
                <p className="text-green-600 font-semibold">
                  R$ {selectedProperty?.valor.toLocaleString('pt-BR')}
                </p>
              </div>
              <p className="text-red-600 text-sm mt-4">
                Esta ação não pode ser desfeita.
              </p>
            </div>
            <div className="flex justify-end gap-4">
              <button
                onClick={() => setShowDeleteModal(false)}
                className="btn btn-secondary"
              >
                Cancelar
              </button>
              <button
                onClick={confirmDelete}
                className="btn bg-red-600 hover:bg-red-700 text-white"
              >
                <FaTrash />
                Excluir
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default MyProperties; 