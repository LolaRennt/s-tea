package org.sky.auto.data.testng.excel;

import java.lang.reflect.Method;
import java.util.Iterator;

import org.databene.benerator.Generator;
import org.databene.benerator.anno.AnnotationMapper;
import org.databene.benerator.anno.DefaultPathResolver;
import org.databene.benerator.engine.BeneratorContext;
import org.databene.benerator.engine.DefaultBeneratorContext;
import org.databene.benerator.factory.EquivalenceGeneratorFactory;
import org.databene.model.data.DataModel;
import org.testng.annotations.DataProvider;

public class TestNGFeed {
	@DataProvider
	public Iterator<Object[]> feeder(Method testMethod){
		Class<?> testClass = testMethod.getDeclaringClass();
		EquivalenceGeneratorFactory generatorFactory=new EquivalenceGeneratorFactory();
		DefaultPathResolver pathResolver = new DefaultPathResolver();
		DataModel dataModel = new DataModel();
		AnnotationMapper mapper = new AnnotationMapper(generatorFactory, dataModel, pathResolver);
		BeneratorContext context = new DefaultBeneratorContext();
		context.setGeneratorFactory(generatorFactory);
		mapper.parseClassAnnotations(testClass.getAnnotations(), context);
		Generator<Object[]>generator = mapper.createAndInitMethodParamsGenerator(testMethod, context);
		return new FeedIterator(generator);
	}
}
