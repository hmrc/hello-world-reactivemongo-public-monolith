package uk.gov.hmrc.helloworldreactivemongo.schedulers

import java.util.concurrent.TimeUnit

import akka.actor.ActorSystem
import com.google.inject.AbstractModule
import javax.inject.Inject
import play.api.Logger
import uk.gov.hmrc.helloworldreactivemongo.config.KillConfig

import scala.concurrent.ExecutionContext

class MongoKillScheduler @Inject()(config: KillConfig, actorSystem: ActorSystem)(
                                  implicit val ec: ExecutionContext) {

  private val logger = Logger(getClass)

  config.helloKillTimer match {
    case Some(delay) =>
      actorSystem.scheduler.scheduleOnce(delay) {
        logger.warn("Killing service..")
        System.exit(0)
      }
    case None => logger.info("Kill testing is disabled")
  }

}

class MongoKillSchedulerModule extends AbstractModule {
  override def configure(): Unit =
    bind(classOf[MongoKillScheduler]).asEagerSingleton()
}
