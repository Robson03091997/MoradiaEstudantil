import React from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';
import { useProperty } from '../contexts/PropertyContext';
import { FaHome, FaPlus, FaChartBar, FaUsers, FaMapMarkerAlt } from 'react-icons/fa';

const Dashboard = () => {
  const { user } = useAuth();
  const { getPropertiesByOwner } = useProperty();
  
  const userProperties = getPropertiesByOwner(user?.id);
  const totalValue = userProperties.reduce((sum, p) => sum + p.valor, 0);

  const StatCard = ({ title, value, icon: Icon, color = 'blue', link = null }) => {
    const content = (
      <div className="card">
        <div className="flex items-center justify-between">
          <div>
            <p className="text-gray-600 text-sm">{title}</p>
            <p className={`text-2xl font-bold text-${color}-600`}>{value}</p>
          </div>
          <div className={`text-${color}-600 text-2xl`}>
            <Icon />
          </div>
        </div>
      </div>
    );

    if (link) {
      return <Link to={link}>{content}</Link>; 
    }
    return content;
  };

  const QuickAction = ({ title, description, icon: Icon, link, color = 'blue' }) => (
    <Link to={link} className="card hover:shadow-lg transition-shadow">
      <div className="flex items-center gap-4">
        <div className={`text-${color}-600 text-2xl`}>
          <Icon />
        </div>
        <div>
          <h3 className="font-semibold text-gray-900">{title}</h3>
          <p className="text-gray-600 text-sm">{description}</p>
        </div>
      </div>
    </Link>
  );

  return (
    <div className="container py-8">
      {/* Header */}
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-gray-900 mb-2">
          Dashboard
        </h1>
        <p className="text-gray-600">
          Bem-vindo de volta, {user?.nome}! Aqui está um resumo dos seus imóveis.
        </p>
      </div>

      {/* Stats */}
      <div className="grid grid-4 gap-6 mb-8">
        <StatCard
          title="Total de Imóveis"
          value={userProperties.length}
          icon={FaHome}
          color="blue"
          link="/my-properties"
        />
        <StatCard
          title="Valor Total"
          value={`R$ ${totalValue.toLocaleString('pt-BR')}`}
          icon={FaChartBar}
          color="green"
        />
        <StatCard
          title="Compartilhados"
          value={userProperties.filter(p => p.compartilhado).length}
          icon={FaUsers}
          color="purple"
        />
        <StatCard
          title="Valor Médio"
          value={`R$ ${userProperties.length > 0 ? Math.round(totalValue / userProperties.length).toLocaleString('pt-BR') : '0'}`}
          icon={FaMapMarkerAlt}
          color="orange"
        />
      </div>

      {/* Quick Actions */}
      <div className="mb-8">
        <h2 className="text-xl font-semibold mb-4">Ações Rápidas</h2>
        <div className="grid grid-3 gap-6">
          <QuickAction
            title="Anunciar Imóvel"
            description="Cadastre um novo imóvel para locação"
            icon={FaPlus}
            link="/add-property"
            color="green"
          />
          <QuickAction
            title="Ver Meus Imóveis"
            description="Gerencie seus imóveis cadastrados"
            icon={FaHome}
            link="/my-properties"
            color="blue"
          />
          <QuickAction
            title="Ver Estatísticas"
            description="Analise dados e relatórios"
            icon={FaChartBar}
            link="/statistics"
            color="purple"
          />
        </div>
      </div>

      {/* Recent Properties */}
      <div className="mb-8">
        <div className="flex items-center justify-between mb-4">
          <h2 className="text-xl font-semibold">Imóveis Recentes</h2>
          <Link to="/my-properties" className="text-blue-600 hover:text-blue-700">
            Ver todos →
          </Link>
        </div>
        
        {userProperties.length > 0 ? (
          <div className="grid grid-3 gap-6">
            {userProperties.slice(0, 3).map(property => (
              <Link key={property.id} to={`/properties/${property.id}`} className="card property-card">
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
                </div>
              </Link>
            ))}
          </div>
        ) : (
          <div className="empty-state">
            <FaHome size={64} className="text-gray-300 mx-auto mb-4" />
            <h3 className="text-xl font-semibold text-gray-900 mb-2">
              Você ainda não tem imóveis cadastrados
            </h3>
            <p className="text-gray-600 mb-6">
              Comece anunciando seu primeiro imóvel!
            </p>
            <Link to="/add-property" className="btn btn-primary">
              <FaPlus />
              Anunciar Primeiro Imóvel
            </Link>
          </div>
        )}
      </div>

      {/* Market Overview */}
      <div>
        <h2 className="text-xl font-semibold mb-4">Visão Geral do Mercado</h2>
        <div className="grid grid-2 gap-6">
          <div className="card">
            <h3 className="text-lg font-semibold mb-4">Dicas para Proprietários</h3>
            <ul className="space-y-2 text-gray-600">
              <li>• Mantenha suas fotos sempre atualizadas</li>
              <li>• Responda rapidamente aos interessados</li>
              <li>• Ofereça condições flexíveis de pagamento</li>
              <li>• Mantenha o imóvel sempre bem conservado</li>
            </ul>
          </div>
          <div className="card">
            <h3 className="text-lg font-semibold mb-4">Próximos Passos</h3>
            <ul className="space-y-2 text-gray-600">
              <li>• Complete o perfil do seu imóvel</li>
              <li>• Adicione mais fotos e detalhes</li>
              <li>• Configure notificações de interesse</li>
              <li>• Analise as estatísticas de visualização</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard; 