package org.sky.auto.story.listener;

public interface StoryListener {
	public void beforeStory();
	public void afterStory();
	public void beforeScenario();
	public void afterScenario();
	public void beforeStories();
	public void afterStories();
}
