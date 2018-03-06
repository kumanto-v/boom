# boom

Two microservices boom and endpoint. Endpoint calls the boom service with a given level of parallelism.

Only affects the 1.4.0 version of Lagom.

e.g. 

> curl http://localhost:9000/api/endpoint/100

Repeat the command if the circuit breaker won't open or increase future count. After the circuit breaker is open the boom microservice is locked:

jcmd < pid > Thread.print

>...
>
>"boom-impl-internal-dev-mode-akka.actor.default-dispatcher-10" #166 prio=5 os_prio=31 tid=0x00007f8e15d56000 nid=0xf403 waiting for monitor entry [0x0000700009fb1000]
    java.lang.Thread.State: BLOCKED (on object monitor)
 	at play.core.server.LagomReloadableDevServerStart$$anon$1.get(LagomReloadableDevServerStart.scala:138)
 	- waiting to lock <0x00000006c3197320> (a play.core.server.LagomReloadableDevServerStart$$anon$1)
 	at play.core.server.AkkaHttpServer.modelConversion(AkkaHttpServer.scala:183)
 	at play.core.server.AkkaHttpServer.handleRequest(AkkaHttpServer.scala:189)
 	at play.core.server.AkkaHttpServer.$anonfun$createServerBinding$1(AkkaHttpServer.scala:106)
 	at play.core.server.AkkaHttpServer$$Lambda$1336/745387905.apply(Unknown Source)
 	at akka.stream.impl.fusing.MapAsync$$anon$24.onPush(Ops.scala:1189)
 	at akka.stream.impl.fusing.GraphInterpreter.processPush(GraphInterpreter.scala:512)
 	at akka.stream.impl.fusing.GraphInterpreter.processEvent(GraphInterpreter.scala:475)
 	at akka.stream.impl.fusing.GraphInterpreter.execute(GraphInterpreter.scala:371)
 	at akka.stream.impl.fusing.GraphInterpreterShell.runBatch(ActorGraphInterpreter.scala:585)
 	at akka.stream.impl.fusing.GraphInterpreterShell$AsyncInput.execute(ActorGraphInterpreter.scala:469)
 	at akka.stream.impl.fusing.GraphInterpreterShell.processEvent(ActorGraphInterpreter.scala:560)
 	at akka.stream.impl.fusing.ActorGraphInterpreter.akka$stream$impl$fusing$ActorGraphInterpreter$$processEvent(ActorGraphInterpreter.scala:742)
 	at akka.stream.impl.fusing.ActorGraphInterpreter$$anonfun$receive$1.applyOrElse(ActorGraphInterpreter.scala:757)
 	at akka.actor.Actor.aroundReceive(Actor.scala:517)
 	at akka.actor.Actor.aroundReceive$(Actor.scala:515)
 	at akka.stream.impl.fusing.ActorGraphInterpreter.aroundReceive(ActorGraphInterpreter.scala:667)
 	at akka.actor.ActorCell.receiveMessage(ActorCell.scala:527)
 	at akka.actor.ActorCell.invoke(ActorCell.scala:496)
 	at akka.dispatch.Mailbox.processMailbox(Mailbox.scala:257)
 	at akka.dispatch.Mailbox.run(Mailbox.scala:224)
 	at akka.dispatch.Mailbox.exec(Mailbox.scala:234)
 	at akka.dispatch.forkjoin.ForkJoinTask.doExec(ForkJoinTask.java:260)
 	at akka.dispatch.forkjoin.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1339)
 	at akka.dispatch.forkjoin.ForkJoinPool.runWorker(ForkJoinPool.java:1979)
 	at akka.dispatch.forkjoin.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:107)
> 
>...
>
> "boom-impl-application-akka.actor.default-dispatcher-7" #172 prio=5 os_prio=31 tid=0x00007f8e1b18d000 nid=0x10003 waiting on condition [0x000070000abc3000]
    java.lang.Thread.State: WAITING (parking)
 	at sun.misc.Unsafe.park(Native Method)
 	- parking to wait for  <0x0000000773211058> (a scala.concurrent.impl.Promise$CompletionLatch)
 	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
 	at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(AbstractQueuedSynchronizer.java:836)
 	at java.util.concurrent.locks.AbstractQueuedSynchronizer.doAcquireSharedInterruptibly(AbstractQueuedSynchronizer.java:997)
 	at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireSharedInterruptibly(AbstractQueuedSynchronizer.java:1304)
 	at scala.concurrent.impl.Promise$DefaultPromise.tryAwait(Promise.scala:238)
 	at scala.concurrent.impl.Promise$DefaultPromise.ready(Promise.scala:254)
 	at scala.concurrent.impl.Promise$DefaultPromise.result(Promise.scala:259)
 	at scala.concurrent.Await$.$anonfun$result$1(package.scala:215)
 	at scala.concurrent.Await$$$Lambda$244/2081462138.apply(Unknown Source)
 	at akka.dispatch.MonitorableThreadFactory$AkkaForkJoinWorkerThread$$anon$3.block(ThreadPoolBuilder.scala:167)
 	at akka.dispatch.forkjoin.ForkJoinPool.managedBlock(ForkJoinPool.java:3641)
 	at akka.dispatch.MonitorableThreadFactory$AkkaForkJoinWorkerThread.blockOn(ThreadPoolBuilder.scala:165)
 	at akka.dispatch.BatchingExecutor$BlockableBatch.blockOn(BatchingExecutor.scala:106)
 	at scala.concurrent.Await$.result(package.scala:142)
 	at play.core.server.LagomReloadableDevServerStart$$anon$1.get(LagomReloadableDevServerStart.scala:215)
 	- locked <0x00000006c3197320> (a play.core.server.LagomReloadableDevServerStart$$anon$1)
 	at play.core.server.AkkaHttpServer.modelConversion(AkkaHttpServer.scala:183)
 	at play.core.server.AkkaHttpServer.$anonfun$runAction$5(AkkaHttpServer.scala:314)
 	at play.core.server.AkkaHttpServer$$Lambda$2821/1451427402.apply(Unknown Source)
 	at scala.concurrent.Future.$anonfun$flatMap$1(Future.scala:304)
 	at scala.concurrent.Future$$Lambda$1923/464770613.apply(Unknown Source)
 	at scala.concurrent.impl.Promise.$anonfun$transformWith$1(Promise.scala:37)
 	at scala.concurrent.impl.Promise$$Lambda$1924/270507202.apply(Unknown Source)
 	at scala.concurrent.impl.CallbackRunnable.run(Promise.scala:60)
 	at akka.dispatch.BatchingExecutor$AbstractBatch.processBatch(BatchingExecutor.scala:55)
 	at akka.dispatch.BatchingExecutor$BlockableBatch.$anonfun$run$1(BatchingExecutor.scala:91)
 	at akka.dispatch.BatchingExecutor$BlockableBatch$$Lambda$2297/224780276.apply$mcV$sp(Unknown Source)
 	at scala.runtime.java8.JFunction0$mcV$sp.apply(JFunction0$mcV$sp.java:12)
 	at scala.concurrent.BlockContext$.withBlockContext(BlockContext.scala:81)
 	at akka.dispatch.BatchingExecutor$BlockableBatch.run(BatchingExecutor.scala:91)
 	at akka.dispatch.TaskInvocation.run(AbstractDispatcher.scala:40)
 	at akka.dispatch.ForkJoinExecutorConfigurator$AkkaForkJoinTask.exec(ForkJoinExecutorConfigurator.scala:43)
 	at akka.dispatch.forkjoin.ForkJoinTask.doExec(ForkJoinTask.java:260)
 	at akka.dispatch.forkjoin.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1339)
 	at akka.dispatch.forkjoin.ForkJoinPool.runWorker(ForkJoinPool.java:1979)
 	at akka.dispatch.forkjoin.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:107)
>
> ...
