package com.example.springwebfluxdemo.redis.config;

import org.springframework.session.ReactiveSessionRepository;
import org.springframework.session.Session;
import org.springframework.web.server.WebSession;
import org.springframework.web.server.session.WebSessionStore;
import reactor.core.publisher.Mono;

public class SpringSessionWebSessionStore<S extends Session> implements WebSessionStore {

	private final ReactiveSessionRepository<S> sessions;

	public SpringSessionWebSessionStore(ReactiveSessionRepository<S> reactiveSessionRepository) {
		this.sessions = reactiveSessionRepository;
	}

	/**
	 * Create a new WebSession.
	 * <p>Note that this does nothing more than create a new instance.
	 * The session can later be started explicitly via {@link WebSession#start()}
	 * or implicitly by adding attributes -- and then persisted via
	 * {@link WebSession#save()}.
	 *
	 * @return the created session instance
	 */
	@Override
	public Mono<WebSession> createWebSession() {
		return null;
	}

	/**
	 * Return the WebSession for the given id.
	 * <p><strong>Note:</strong> This method should perform an expiration check,
	 * and if it has expired remove the session and return empty. This method
	 * should also update the lastAccessTime of retrieved sessions.
	 *
	 * @param sessionId the session to load
	 * @return the session, or an empty {@code Mono} .
	 */
	@Override
	public Mono<WebSession> retrieveSession(String sessionId) {
		return null;
	}

	/**
	 * Remove the WebSession for the specified id.
	 *
	 * @param sessionId the id of the session to remove
	 * @return a completion notification (success or error)
	 */
	@Override
	public Mono<Void> removeSession(String sessionId) {
		return null;
	}

	/**
	 * Update the last accessed timestamp to "now".
	 *
	 * @param webSession the session to update
	 * @return the session with the updated last access time
	 */
	@Override
	public Mono<WebSession> updateLastAccessTime(WebSession webSession) {
		return null;
	}

	// ...
}