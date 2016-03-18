package br.com.prodonto.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory fabricaDeSessoes = criarFabricaDeSessoes();

	public static SessionFactory getFabricaDeSessoes() {
		return fabricaDeSessoes;
	}

	private static SessionFactory criarFabricaDeSessoes() {
		try {
			// busca o hibernate.cfg e carrega as propriedades configuradas nele
			Configuration configuracao = new Configuration().configure();
			
			// Envia para o registro quais são as configurações 'configuracao'
			ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties()).build();
			
			// constóri a fábrica de sessões com o banco de dados
			SessionFactory fabrica = configuracao.buildSessionFactory(registro);
			
			// retorna a fábrica de sessões (Retorna para a variável 'fabricaDeSessoes')
			return fabrica;
		} catch (Throwable ex) {
			System.err.println("A fábrica de sessões não pode ser criada:" + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
}
