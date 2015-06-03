package com.groupon.jenkins.dynamic.build.api.metrics;

import org.kohsuke.stapler.export.Exported;
import org.kohsuke.stapler.export.ExportedBean;

@ExportedBean(defaultVisibility = 100)
public abstract class Chart {
  @Exported(inline = true)
  public abstract ChartType getType();
  @Exported(inline = true)
  public abstract Object value();
}
