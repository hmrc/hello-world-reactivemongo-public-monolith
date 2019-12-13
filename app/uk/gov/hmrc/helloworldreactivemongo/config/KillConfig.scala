package uk.gov.hmrc.helloworldreactivemongo.config

import javax.inject.Inject
import play.api.Configuration

import scala.concurrent.duration._
import scala.language.postfixOps

class KillConfig @Inject()(configuration: Configuration) {
  def helloKillEnabled: Boolean = configuration.getBoolean("hello.kill.enabled").getOrElse(false)

  def helloKillTimer: FiniteDuration = configuration.getMilliseconds("hello.kill.timer").map(_.millisecond).getOrElse(5 minute)
}
