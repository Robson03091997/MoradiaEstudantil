import React, { createContext, useContext, useState, useEffect } from 'react';

const PropertyContext = createContext();

// Dados de exemplo dos imóveis (simulando um banco de dados)
const mockProperties = [
  {
    id: 1,
    tipo: 'Casa',
    endereco: 'Rua das Flores, 123 - Centro',
    banheiros: 2,
    area: 120.0,
    maxInquilinos: 3,
    valor: 2500.0,
    quartos: 3,
    garagem: 1,
    proprietario: {
      id: 1,
      nome: 'João',
      sobrenome: 'Silva',
      email: 'joao@email.com'
    },
    compartilhado: false,
    tipoCompartilhamento: null,
    responsavel: null,
    imagem: 'https://images.unsplash.com/photo-1564013799919-ab600027ffc6?w=400'
  },
  {
    id: 2,
    tipo: 'Kitnet',
    endereco: 'Av. Principal, 456 - Jardim',
    banheiros: 1,
    area: 35.0,
    maxInquilinos: 1,
    valor: 1200.0,
    cozinha: true,
    sacada: true,
    proprietario: {
      id: 2,
      nome: 'Maria',
      sobrenome: 'Santos',
      email: 'maria@email.com'
    },
    compartilhado: false,
    tipoCompartilhamento: null,
    responsavel: null,
    imagem: 'https://images.unsplash.com/photo-1522708323590-d24dbb6b0267?w=400'
  },
  {
    id: 3,
    tipo: 'Quarto',
    endereco: 'Rua da Universidade, 789 - Campus',
    banheiros: 1,
    area: 15.0,
    maxInquilinos: 1,
    valor: 800.0,
    banheiroPrivativo: true,
    arCondicionado: true,
    proprietario: {
      id: 3,
      nome: 'Carlos',
      sobrenome: 'Oliveira',
      email: 'carlos@email.com'
    },
    compartilhado: false,
    tipoCompartilhamento: null,
    responsavel: null,
    imagem: 'https://images.unsplash.com/photo-1586023492125-27b2c045efd7?w=400'
  },
  {
    id: 4,
    tipo: 'República',
    endereco: 'Rua dos Estudantes, 321 - Vila Nova',
    banheiros: 2,
    area: 100.0,
    maxInquilinos: 5,
    valor: 2500.0,
    quartos: 5,
    cozinhaCompartilhada: true,
    internet: true,
    proprietario: {
      id: 1,
      nome: 'João',
      sobrenome: 'Silva',
      email: 'joao@email.com'
    },
    compartilhado: true,
    tipoCompartilhamento: 'MISTO',
    responsavel: null,
    imagem: 'https://images.unsplash.com/photo-1560448204-e02f11c3d0e2?w=400'
  },
  {
    id: 5,
    tipo: 'Pensionato',
    endereco: 'Rua dos Estudantes, 654 - Vila Nova',
    banheiros: 2,
    area: 120.0,
    maxInquilinos: 6,
    valor: 3000.0,
    quartos: 6,
    refeicoesInclusas: true,
    limpezaInclusa: true,
    horarioFechamento: true,
    regrasRigorosas: true,
    proprietario: {
      id: 2,
      nome: 'Maria',
      sobrenome: 'Santos',
      email: 'maria@email.com'
    },
    compartilhado: true,
    tipoCompartilhamento: 'MISTO',
    responsavel: null,
    imagem: 'https://images.unsplash.com/photo-1554995207-c18c203602cb?w=400'
  },
  {
    id: 6,
    tipo: 'Edícula',
    endereco: 'Rua do Comércio, 654 - Centro',
    banheiros: 1,
    area: 45.0,
    maxInquilinos: 2,
    valor: 1500.0,
    proprietario: {
      id: 3,
      nome: 'Carlos',
      sobrenome: 'Oliveira',
      email: 'carlos@email.com'
    },
    compartilhado: false,
    tipoCompartilhamento: null,
    responsavel: null,
    imagem: 'https://images.unsplash.com/photo-1570129477492-45c003edd2be?w=400'
  },
  {
    id: 7,
    tipo: 'Prédio de Kitnets',
    endereco: 'Av. das Indústrias, 987 - Industrial',
    banheiros: 1,
    area: 40.0,
    maxInquilinos: 1,
    valor: 1100.0,
    andares: 3,
    kitnets: 12,
    proprietario: {
      id: 1,
      nome: 'João',
      sobrenome: 'Silva',
      email: 'joao@email.com'
    },
    compartilhado: false,
    tipoCompartilhamento: null,
    responsavel: null,
    imagem: 'https://images.unsplash.com/photo-1545324418-cc1a3fa10c00?w=400'
  }
];

export const PropertyProvider = ({ children }) => {
  const [properties, setProperties] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // Simular carregamento dos dados
    setTimeout(() => {
      setProperties(mockProperties);
      setLoading(false);
    }, 500);
  }, []);

  const addProperty = (propertyData) => {
    const newProperty = {
      id: properties.length + 1,
      ...propertyData,
      imagem: 'https://images.unsplash.com/photo-1564013799919-ab600027ffc6?w=400' // Imagem padrão
    };
    
    setProperties(prev => [...prev, newProperty]);
    return { success: true, property: newProperty };
  };

  const getPropertyById = (id) => {
    return properties.find(p => p.id === parseInt(id));
  };

  const getPropertiesByOwner = (ownerId) => {
    return properties.filter(p => p.proprietario.id === ownerId);
  };

  const getPropertiesByType = (tipo) => {
    return properties.filter(p => p.tipo === tipo);
  };

  const getStatistics = () => {
    const stats = {
      total: properties.length,
      porTipo: {},
      valorMedio: {},
      valorTotal: properties.reduce((sum, p) => sum + p.valor, 0)
    };

    properties.forEach(property => {
      if (!stats.porTipo[property.tipo]) {
        stats.porTipo[property.tipo] = 0;
        stats.valorMedio[property.tipo] = { total: 0, count: 0 };
      }
      
      stats.porTipo[property.tipo]++;
      stats.valorMedio[property.tipo].total += property.valor;
      stats.valorMedio[property.tipo].count++;
    });

    // Calcular médias
    Object.keys(stats.valorMedio).forEach(tipo => {
      const { total, count } = stats.valorMedio[tipo];
      stats.valorMedio[tipo] = total / count;
    });

    return stats;
  };

  const value = {
    properties,
    loading,
    addProperty,
    getPropertyById,
    getPropertiesByOwner,
    getPropertiesByType,
    getStatistics
  };

  return (
    <PropertyContext.Provider value={value}>
      {children}
    </PropertyContext.Provider>
  );
};

export const useProperty = () => {
  const context = useContext(PropertyContext);
  if (!context) {
    throw new Error('useProperty deve ser usado dentro de um PropertyProvider');
  }
  return context;
}; 