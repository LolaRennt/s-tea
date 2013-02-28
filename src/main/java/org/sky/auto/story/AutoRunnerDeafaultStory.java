package org.sky.auto.story;

import java.util.List;
import java.util.Set;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.Test;
import org.sky.auto.anno.StoryRegister;
import org.sky.auto.base.ConfigParser;
import org.sky.auto.intrumentation.ClassPool;
import org.sky.auto.steps.AutoSteps;
import org.sky.auto.story.listener.ProxyStoryListener;
import org.sky.auto.story.listener.StoryListener;

public class AutoRunnerDeafaultStory extends JUnitStories{

	@Override
	protected List<String> storyPaths() {
		return new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(this.getClass()), "**/*.story", "");

	}

	
	@Override
	public Configuration configuration() {
		return new MostUsefulConfiguration().useStoryLoader(new LoadFromClasspath(this.getClass()))
				.useStoryReporterBuilder(new StoryReporterBuilder()
				.withCodeLocation(CodeLocations.codeLocationFromClass(this.getClass()))
				.withDefaultFormats().withFormats(Format.CONSOLE,Format.HTML).withFailureTrace(true)
				.withFailureTraceCompression(true));
				
	}
	
	@Test
    public void run() throws Throwable {
		Set<Class<?>> cls =ClassPool.getClassPool();
		for(Class<?> clazz:cls){
			if(clazz.isAnnotationPresent(StoryRegister.class)){
				ProxyStoryListener.register((StoryListener) clazz.newInstance());
			}
		}
        Embedder embedder = configuredEmbedder();
        embedder.embedderControls().useThreads(ConfigParser.getStoryThreads());
        try {
            embedder.runStoriesAsPaths(storyPaths());
        } finally {
            embedder.generateCrossReference();
        }
    }
	
	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(),new AutoSteps());
	}
	
	
	
	
}
