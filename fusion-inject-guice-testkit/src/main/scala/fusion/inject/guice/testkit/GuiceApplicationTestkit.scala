/*
 * Copyright 2019 akka-fusion.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fusion.inject.guice.testkit

import akka.actor.typed.{ ActorRef, ActorSystem, Behavior, Props }
import akka.fusion.AkkaUtils
import akka.{ actor => classic }
import com.typesafe.config.Config
import fusion.common.{ ReceptionistFactory, SpawnFactory }
import fusion.core.FusionApplication
import fusion.inject.guice.GuiceApplication
import fusion.testkit.FusionScalaFutures
import helloscala.common.Configuration
import javax.inject.Named
import org.scalatest.concurrent.Eventually
import org.scalatest.matchers.should.Matchers
import org.scalatest.{ BeforeAndAfterAll, EitherValues, OptionValues, TestSuite }

import scala.concurrent.duration._
import scala.reflect.ClassTag

abstract class GuiceApplicationTestkit(val application: GuiceApplication)
    extends TestSuite
    with Matchers
    with BeforeAndAfterAll
    with FusionScalaFutures
    with Eventually
    with OptionValues
    with EitherValues
    with SpawnFactory
    with ReceptionistFactory {
  def this() = this(FusionApplication.start().asInstanceOf[GuiceApplication])

  def config: Config = application.config

  def configuration: Configuration = Configuration(config)

  override def typedSystem: ActorSystem[_] = application.typedSystem

  def classicSystem: classic.ActorSystem = application.classicSystem

  override def spawn[T](behavior: Behavior[T], props: Props): ActorRef[T] = application.spawn(behavior, props)

  override def spawn[T](behavior: Behavior[T], name: String, props: Props): ActorRef[T] =
    application.spawn(behavior, name, props)

  def injectInstance[T](implicit ev: ClassTag[T]): T = application.injector.instance[T]

  def injectInstance[T](a: Named)(implicit ev: ClassTag[T]): T = application.injector.instance[T](a)

  override protected def afterAll(): Unit = {
    AkkaUtils.shutdownActorSystem(application.classicSystem, 60.seconds)
  }
}
