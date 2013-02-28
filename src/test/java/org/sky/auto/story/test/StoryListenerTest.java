package org.sky.auto.story.test;

import org.sky.auto.anno.StoryRegister;
import org.sky.auto.base.AutoBase;
import org.sky.auto.story.listener.StoryListener;

@StoryRegister
public class StoryListenerTest implements StoryListener{

	@Override
	public void beforeStory() {
		//System.out.println("故事开始了！");
		
	}

	@Override
	public void afterStory() {
		// TODO Auto-generated method stub
		if(!AutoBase.isClose_Status()){
			AutoBase.closeAllWindow();
		}
	}

	@Override
	public void beforeScenario() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterScenario() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeStories() {
		//System.out.println("故事开始了亲！");
		
	}

	@Override
	public void afterStories() {
		// TODO Auto-generated method stub
		
	}

}
