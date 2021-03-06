/*
 * Copyright 2019 helloscala.com
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

package fusion.http.client

import java.util.Objects

import akka.actor.typed.ActorSystem
import akka.{ actor => classic }
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.marshalling.Marshaller
import akka.http.scaladsl.model._
import akka.stream.Materializer

import scala.collection.immutable
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class DefaultHttpClient private ()(implicit val classicSystem: classic.ActorSystem) extends HttpClient {
  /**
   * 发送 Http 请求
   *
   * @param method   请求方法类型
   * @param uri      请求地址
   * @param params   请求URL查询参数
   * @param entity     请求数据（将备序列化成JSON）
   * @param headers  请求头
   * @param protocol HTTP协议版本
   * @return HttpResponse
   */
  def singleRequest[T](
      method: HttpMethod,
      uri: Uri,
      params: Seq[(String, String)] = Nil,
      entity: T = HttpEntity.Empty,
      headers: immutable.Seq[HttpHeader] = Nil,
      protocol: HttpProtocol = HttpProtocols.`HTTP/1.1`)(
      implicit
      m: Marshaller[T, RequestEntity],
      mat: Materializer,
      ec: ExecutionContext = null): Future[HttpResponse] = {
    val eec = if (Objects.isNull(ec)) mat.executionContext else ec
    Marshal(entity)
      .to[RequestEntity](m, eec)
      .flatMap(payload => singleRequest(HttpRequest(method, uri, headers, payload, protocol)))(eec)
  }

  override def singleRequest(req: HttpRequest): Future[HttpResponse] = Http().singleRequest(req)

  override def close(): Unit = {}
}

object DefaultHttpClient {
  import akka.actor.typed.scaladsl.adapter._
  def apply(system: ActorSystem[_]): DefaultHttpClient = apply(system.toClassic)
  def apply(system: classic.ActorSystem): DefaultHttpClient = new DefaultHttpClient()(system)
}
