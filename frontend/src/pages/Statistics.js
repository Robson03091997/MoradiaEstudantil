import React from 'react';
import { useProperty } from '../contexts/PropertyContext';
import { FaHome, FaChartBar, FaUsers, FaMoneyBillWave } from 'react-icons/fa';

const Statistics = () => {
  const { properties } = useProperty();

  // Cálculos estatísticos
  const totalProperties = properties.length;
  const totalValue = properties.reduce((sum, p) => sum + p.valor, 0);
  const averageValue = totalProperties > 0 ? totalValue / totalProperties : 0;
  const sharedProperties = properties.filter(p => p.compartilhado).length;
  const sharedPercentage = totalProperties > 0 ? (sharedProperties / totalProperties) * 100 : 0;

  // Estatísticas por tipo
  const typeStats = properties.reduce((acc, property) => {
    acc[property.tipo] = (acc[property.tipo] || 0) + 1;
    return acc;
  }, {});

  // Top 5 tipos mais comuns
  const topTypes = Object.entries(typeStats)
    .sort(([,a], [,b]) => b - a)
    .slice(0, 5);

  // Estatísticas por região (primeira parte do endereço)
  const regionStats = properties.reduce((acc, property) => {
    const region = property.endereco.split(',')[0].trim();
    acc[region] = (acc[region] || 0) + 1;
    return acc;
  }, {});

  const topRegions = Object.entries(regionStats)
    .sort(([,a], [,b]) => b - a)
    .slice(0, 5);

  // Faixas de preço
  const priceRanges = {
    'Até R$ 500': properties.filter(p => p.valor <= 500).length,
    'R$ 501 - R$ 1.000': properties.filter(p => p.valor > 500 && p.valor <= 1000).length,
    'R$ 1.001 - R$ 2.000': properties.filter(p => p.valor > 1000 && p.valor <= 2000).length,
    'R$ 2.001 - R$ 3.000': properties.filter(p => p.valor > 2000 && p.valor <= 3000).length,
    'Acima de R$ 3.000': properties.filter(p => p.valor > 3000).length,
  };

  const StatCard = ({ title, value, icon: Icon, color = 'blue' }) => (
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

  const ChartCard = ({ title, data, type = 'bar' }) => (
    <div className="card">
      <h3 className="text-lg font-semibold mb-4">{title}</h3>
      <div className="space-y-3">
        {data.map(([label, value], index) => (
          <div key={index} className="flex items-center justify-between">
            <span className="text-gray-600">{label}</span>
            <div className="flex items-center gap-2">
              <div className="w-32 bg-gray-200 rounded-full h-2">
                <div 
                  className="bg-blue-600 h-2 rounded-full"
                  style={{ width: `${(value / Math.max(...data.map(([,v]) => v))) * 100}%` }}
                ></div>
              </div>
              <span className="text-sm font-semibold text-gray-900">{value}</span>
            </div>
          </div>
        ))}
      </div>
    </div>
  );

  return (
    <div className="container py-8">
      {/* Header */}
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-gray-900 mb-2">
          Estatísticas do Sistema
        </h1>
        <p className="text-gray-600">
          Análise completa dos dados de imóveis cadastrados
        </p>
      </div>

      {/* Main Stats */}
      <div className="grid grid-4 gap-6 mb-8">
        <StatCard
          title="Total de Imóveis"
          value={totalProperties}
          icon={FaHome}
          color="blue"
        />
        <StatCard
          title="Valor Total"
          value={`R$ ${totalValue.toLocaleString('pt-BR')}`}
          icon={FaMoneyBillWave}
          color="green"
        />
        <StatCard
          title="Valor Médio"
          value={`R$ ${Math.round(averageValue).toLocaleString('pt-BR')}`}
          icon={FaChartBar}
          color="purple"
        />
        <StatCard
          title="Compartilhados"
          value={`${sharedProperties} (${Math.round(sharedPercentage)}%)`}
          icon={FaUsers}
          color="orange"
        />
      </div>

      <div className="grid grid-2 gap-8">
        {/* Charts */}
        <div className="space-y-6">
          <ChartCard
            title="Imóveis por Tipo"
            data={topTypes}
          />
          
          <ChartCard
            title="Imóveis por Região"
            data={topRegions}
          />
        </div>

        {/* Price Distribution */}
        <div className="space-y-6">
          <div className="card">
            <h3 className="text-lg font-semibold mb-4">Distribuição por Preço</h3>
            <div className="space-y-4">
              {Object.entries(priceRanges).map(([range, count]) => (
                <div key={range} className="flex items-center justify-between">
                  <span className="text-gray-600">{range}</span>
                  <div className="flex items-center gap-2">
                    <div className="w-32 bg-gray-200 rounded-full h-2">
                      <div 
                        className="bg-green-600 h-2 rounded-full"
                        style={{ width: `${(count / totalProperties) * 100}%` }}
                      ></div>
                    </div>
                    <span className="text-sm font-semibold text-gray-900">{count}</span>
                  </div>
                </div>
              ))}
            </div>
          </div>

          {/* Additional Stats */}
          <div className="card">
            <h3 className="text-lg font-semibold mb-4">Informações Adicionais</h3>
            <div className="space-y-3">
              <div className="flex justify-between">
                <span className="text-gray-600">Imóvel mais caro:</span>
                <span className="font-semibold">
                  R$ {Math.max(...properties.map(p => p.valor)).toLocaleString('pt-BR')}
                </span>
              </div>
              <div className="flex justify-between">
                <span className="text-gray-600">Imóvel mais barato:</span>
                <span className="font-semibold">
                  R$ {Math.min(...properties.map(p => p.valor)).toLocaleString('pt-BR')}
                </span>
              </div>
              <div className="flex justify-between">
                <span className="text-gray-600">Maior área:</span>
                <span className="font-semibold">
                  {Math.max(...properties.map(p => p.area))}m²
                </span>
              </div>
              <div className="flex justify-between">
                <span className="text-gray-600">Menor área:</span>
                <span className="font-semibold">
                  {Math.min(...properties.map(p => p.area))}m²
                </span>
              </div>
              <div className="flex justify-between">
                <span className="text-gray-600">Total de banheiros:</span>
                <span className="font-semibold">
                  {properties.reduce((sum, p) => sum + p.banheiros, 0)}
                </span>
              </div>
              <div className="flex justify-between">
                <span className="text-gray-600">Capacidade total:</span>
                <span className="font-semibold">
                  {properties.reduce((sum, p) => sum + p.maxInquilinos, 0)} pessoas
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* Summary */}
      <div className="mt-8">
        <div className="card bg-gradient-to-r from-blue-500 to-blue-600 text-white">
          <h3 className="text-xl font-semibold mb-4">Resumo Executivo</h3>
          <div className="grid grid-3 gap-6">
            <div>
              <p className="text-sm opacity-90">Tipo Mais Popular</p>
              <p className="text-lg font-semibold">
                {topTypes.length > 0 ? topTypes[0][0] : 'N/A'}
              </p>
            </div>
            <div>
              <p className="text-sm opacity-90">Região Mais Popular</p>
              <p className="text-lg font-semibold">
                {topRegions.length > 0 ? topRegions[0][0] : 'N/A'}
              </p>
            </div>
            <div>
              <p className="text-sm opacity-90">Faixa de Preço Dominante</p>
              <p className="text-lg font-semibold">
                {Object.entries(priceRanges).reduce((a, b) => a[1] > b[1] ? a : b)[0]}
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Statistics; 