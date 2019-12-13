package uk.gov.hmrc.helloworldreactivemongo.schedulers

import java.util.concurrent.TimeUnit

import akka.actor.ActorSystem
import com.google.inject.AbstractModule
import javax.inject.Inject
import play.api.Logger
import uk.gov.hmrc.helloworldreactivemongo.config.KillConfig

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.FiniteDuration

class MongoKillScheduler @Inject()(config: KillConfig, actorSystem: ActorSystem)(
                                  implicit val ec: ExecutionContext) {

  private val logger = Logger(getClass)

  val interval: FiniteDuration = FiniteDuration(24, TimeUnit.HOURS)

  actorSystem.scheduler.schedule(config.helloKillTimer, interval) {
    if (config.helloKillEnabled) {
      logger.warn("Killing service..")
      System.exit(0)
    } else {
      logger.info("Kill testing is disabled")
    }

  }

}

class MongoKillSchedulerModule extends AbstractModule {
  override def configure(): Unit =
    bind(classOf[MongoKillScheduler]).asEagerSingleton()
}
